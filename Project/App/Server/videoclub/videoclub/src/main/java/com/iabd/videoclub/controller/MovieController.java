package com.iabd.videoclub.controller;

import com.iabd.videoclub.exception.ResourceNotFoundException;
import com.iabd.videoclub.models.Movie;
import com.iabd.videoclub.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long movieId)
            throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("movie not found for this id :: " + movieId));
        return ResponseEntity.ok().body(movie);
    }
    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable(value = "id") Long movieId,
                                           @Valid @RequestBody Movie movieDetails) throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("movie not found for this id :: " + movieId));

        movie.setDirector(movieDetails.getDirector());
        movie.setGender(movieDetails.getGender());
        movie.setPoster(movieDetails.getPoster());
        movie.setSynopsis(movieDetails.getSynopsis());
        movie.setTitle(movieDetails.getTitle());
        final Movie updateMovie = movieRepository.save(movie);
        return ResponseEntity.ok(updateMovie);
    }

    @DeleteMapping("/movies/{id}")
    public Map<String, Boolean> deleteMovie(@PathVariable(value = "id") Long movieeId)
            throws ResourceNotFoundException {
        Movie movie = movieRepository.findById(movieeId)
                .orElseThrow(() -> new ResourceNotFoundException("movie not found for this id :: " + movieeId));

        movieRepository.delete(movie);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
