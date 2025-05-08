package com.hackerthon5.avengers_BE.movie.controller;

import com.hackerthon5.avengers_BE.movie.dto.MovieResponseDTO;
import com.hackerthon5.avengers_BE.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<MovieResponseDTO> getMovies() {
        MovieResponseDTO response = movieService.getMovies();

        return ResponseEntity.ok(response);
    }
}
