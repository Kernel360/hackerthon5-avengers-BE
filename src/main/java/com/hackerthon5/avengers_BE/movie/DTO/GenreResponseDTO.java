package com.hackerthon5.avengers_BE.movie.DTO;

import java.util.List;

public record GenreResponseDTO(
        List<GenreDTO> genres
) {
}
