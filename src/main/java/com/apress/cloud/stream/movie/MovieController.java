package com.apress.cloud.stream.movie;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;

@Log4j2
@AllArgsConstructor
@RequestMapping("/v1/api")
@RestController
public class MovieController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MovieController.class);
	private StreamBridge streamBridge;

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<MovieResponse> toMovieBinding(@RequestBody MovieRequest movieRequest) {
        assert  movieRequest != null;
        movieRequest.setCreated(LocalDateTime.now());

        log.debug("Sending: {} ", movieRequest);
        assert streamBridge != null;
        streamBridge.send("movie-out-0", movieRequest);

        return ResponseEntity
                .accepted()
                .body(new MovieResponse(HttpStatus.OK.value(),"Movies processed: " + ((Collection)movieRequest.getMovies()).size(), LocalDateTime.now()) );
    }
}
