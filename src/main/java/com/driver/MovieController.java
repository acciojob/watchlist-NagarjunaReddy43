package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){

        return new ResponseEntity<>(movieService.addMovie(movie),HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        return new ResponseEntity<>(movieService.addDirector(director),HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirector(@RequestParam("moviename") String moviename,@RequestParam("directorName") String directorName){
        return new ResponseEntity<>(movieService.addMovieDirectorPair(moviename,directorName),HttpStatus.CREATED);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@RequestParam("name") String moviename){
        return new ResponseEntity<>(movieService.getMovieByname(moviename),HttpStatus.FOUND);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        return new ResponseEntity<>(movieService.getDirectorByName(name),HttpStatus.FOUND);
    }
    @GetMapping("/get-movies-by-director-name")
    public ResponseEntity getListofMoviesByDirectorName(@PathVariable("director") String director){
        return new ResponseEntity<>(movieService.getListofMoviesByDirectorName(director),HttpStatus.FOUND);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(),HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-director-by-name")

    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName){
        return new ResponseEntity<>(movieService.deleteDirectorByName(directorName),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        return new ResponseEntity<>(movieService.deleteAllDirectors(),HttpStatus.ACCEPTED);
    }
}
