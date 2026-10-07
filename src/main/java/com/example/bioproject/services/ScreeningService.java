package com.example.bioproject.services;

import com.example.bioproject.dtos.CreateScreeningDTO;
import com.example.bioproject.entities.Movie;
import com.example.bioproject.entities.Screening;
import com.example.bioproject.entities.Theater;
import com.example.bioproject.repositories.ScreeningRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final TheaterService theaterService;
    private final MovieService movieService;

    public ScreeningService(ScreeningRepository screeningRepository,
                            TheaterService theaterService,
                            MovieService movieService) {
        this.screeningRepository = screeningRepository;
        this.theaterService = theaterService;
        this.movieService = movieService;
    }


    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }


    // window.location.href

    //dockerfile
    //compose.yaml fil
    //Database service på compose
    //Application service på compose
    //application skal spændes op på en container og database på en container og så skal de snakke sammen.
    //.github/workflows/ci.yml


    @Transactional
    public void createScreenings(List<CreateScreeningDTO> listDTO) throws Exception {

        List<Screening> screeningsList = getAllScreenings();
        List<Screening> newScreeningsList = new ArrayList<>();

        for (CreateScreeningDTO dto : listDTO) {


            Optional<Movie> movieOpt =
                    movieService.getMovieById(dto.movieId());

            if (movieOpt.isEmpty()) {
                throw new Exception(
                        "Movie object not in dto or movie doesnt exist in database"
                );
            }

            Movie movie = movieOpt.get();


            Optional<Theater> theaterOpt =
                    theaterService.getTheaterById(dto.theaterId());

            if (theaterOpt.isEmpty()) {
                throw new Exception(
                        "Theater doesn't exist in database"
                );
            }

            Theater theater = theaterOpt.get();


            ///Check theater availability and screening time

            //Time for new screening
            LocalDateTime newStartTime = dto.startTime();
            LocalDateTime newEndTime =
                    newStartTime.plusMinutes(movie.getDuration());


            //Check existing screenings and previously looped screenings
            for (Screening s : screeningsList) {

                //Time for existing or previously looped screening
                LocalDateTime existingStartTime =
                        s.getStartTime();

                LocalDateTime existingEndTime =
                        existingStartTime.plusMinutes(
                                s.getMovie().getDuration()
                        );


                //Time check var
                boolean timeOverlap =
                        existingStartTime.isBefore(newEndTime) &&
                                existingEndTime.isAfter(newStartTime);


                //Theater check var
                boolean sameTheater =
                        s.getTheater().getId()
                                .equals(dto.theaterId());


                //Time and theater check
                if (timeOverlap && sameTheater) {

                    throw new Exception(
                            "Theater occupied. Overlapped screening run time: "
                                    + existingStartTime.toLocalTime()
                                    + " to "
                                    + existingEndTime.toLocalTime()
                    );
                }
            }

            Screening screening =
                    new Screening(movie, theater, dto.startTime());

            /// Validations passed, adding to finalized Screening list
            newScreeningsList.add(screening);

            /// Making sure that previous validated screenings also get crosschecked
            screeningsList.add(screening);
        }


        //All validations passed, save new screenings to database
        screeningRepository.saveAll(newScreeningsList);
    }
}