package com.hackerthon5.avengers_BE.movie.repository;

import com.hackerthon5.avengers_BE.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTmdbId(int tmdbId);
}
