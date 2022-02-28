package com.apress.cloud.stream.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieRequest {
    
	String action;
    Iterable<Movie> movies;
    LocalDateTime created;
    
}
