package com.hackerthon5.avengers_BE.movie.service;

import com.hackerthon5.avengers_BE.common.ApiExceptionCode;
import com.hackerthon5.avengers_BE.common.CustomException;
import com.hackerthon5.avengers_BE.movie.DTO.GenreDTO;
import com.hackerthon5.avengers_BE.movie.DTO.GenreResponseDTO;
import com.hackerthon5.avengers_BE.movie.DTO.MovieResponseDTO;
import com.hackerthon5.avengers_BE.movie.domain.Movie;
import com.hackerthon5.avengers_BE.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class MoiveSaveScheduler {
    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String API_KEY = "aab8440059a271595dd697e252651fab";
    private final String BASE_URL = "https://api.themoviedb.org/3";

    @Scheduled(fixedRate = 3600000)
    public void fetchMoviesandGenres() {
        log.info("영화랑 장르 가져오기 시작");

        Map<Integer, String> genreMap = fetchGenres();
        log.info("장르 먼저 가져오기 성공");

        String movieUrl = BASE_URL + "/movie/popular?language=ko-KR&page=1&api_key=" + API_KEY;
        MovieResponseDTO response = restTemplate.getForObject(movieUrl, MovieResponseDTO.class);

        if (response != null && response.results() != null) {
            List<Movie> newMovies = response.results().stream()
                    .map(dto -> Movie.toEntity(dto, genreMap))
                    .filter(movie -> !movieRepository.existsByTmdbId(movie.getTmdbId())) // 중복 검사
                    .toList();

            if (!newMovies.isEmpty()) {
                movieRepository.saveAll(newMovies);
                log.info("{}개의 새 영화 저장 성공", newMovies.size());
            } else {
                log.info("저장할 새 영화 없음");
            }
        }
    }


    public Map<Integer, String> fetchGenres(){
        String genreUrl = BASE_URL + "/genre/movie/list?language=ko&api_key=" + API_KEY;

        GenreResponseDTO genreResponse = restTemplate.getForObject(genreUrl, GenreResponseDTO.class);

        if(genreResponse == null || genreResponse.genres() == null)
            throw new CustomException(ApiExceptionCode.GENRE_NOT_FOUND);

        return genreResponse.genres().stream()
                .collect(Collectors.toMap(GenreDTO::id, GenreDTO::name));
    }
}
