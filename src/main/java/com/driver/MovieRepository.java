package com.driver;

import java.awt.*;
import java.time.format.SignStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
@Repository
public class MovieRepository {
    HashMap<String,Movie> movieMap ;
    HashMap<String,Director> directorMap;

    HashMap<String, List<String>> movie_director_map;

    public MovieRepository(){
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.movie_director_map = new HashMap<>();
    }
    public String addMovie(Movie movie){
        String name = movie.getName();

        if(!movieMap.containsKey(name)){
            movieMap.put(name,movie);
        }

        return "Movie Added Successfully";
    }

    public String addDirector(Director director){
        String name = director.getName();
        if(!directorMap.containsKey(name)){
            directorMap.put(name,director);
        }

        return "Director Added Successfully";
    }

    public String addMovieDirectorPair(String movieName,String directorName) {
        if (!movieMap.containsKey(movieName) || !directorMap.containsKey(directorName))
            return "Movie or director pair is not found in the database";

        if (movie_director_map.containsKey(directorName)) {
            movie_director_map.get(directorName).add(movieName);
        } else {
            List<String> movie = new ArrayList<>();
            movie.add(movieName);
            movie_director_map.put(directorName, movie);
        }

        return "Movie-Director Pair Is Added Successfully";
    }

    public Movie getMovieByname(String movieName){
        return movieMap.get(movieName);
    }
    public Director getDirectorByName(String directorName){
        return directorMap.get(directorName);
    }

    public List<String> getListofMoviesByDirectorName(String directorName){
        return movie_director_map.get(directorName);
    }
    public List<String> findAllMovies(){
        List<String> ans = new ArrayList<>();
        for(String name : movieMap.keySet()){
            ans.add(name);
        }

        return ans;
    }

    public String deleteDirectorByName(String dirName){
        List<String> movies = movie_director_map.get(dirName);

        for(int i=0;i<movies.size();i++){
            if(movieMap.containsKey(movies.get(i))){
                movieMap.remove(movies.get(i));
            }
        }
        movie_director_map.remove(dirName);
        if(directorMap.containsKey(dirName)){
            directorMap.remove(dirName);
        }

        return "Director and its movie removed successfully";
    }

    public String deleteAllDirectors(){
        for(String name : movie_director_map.keySet()){
            List<String> lst = movie_director_map.get(name);

            for(int i=0;i<lst.size();i++){
                if(movieMap.containsKey(lst.get(i))){
                    movieMap.remove(lst.get(i));
                }
            }

            directorMap.remove(name);
        }

        for(String str : directorMap.keySet()){
            directorMap.remove(str);
        }

        return "All Directors and all of their movies removed successfully";
    }
    

}
