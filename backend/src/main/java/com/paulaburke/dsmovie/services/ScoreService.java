package com.paulaburke.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paulaburke.dsmovie.dto.MovieDTO;
import com.paulaburke.dsmovie.dto.ScoreDTO;
import com.paulaburke.dsmovie.entities.Movie;
import com.paulaburke.dsmovie.entities.Score;
import com.paulaburke.dsmovie.entities.User;
import com.paulaburke.dsmovie.repositories.MovieRepository;
import com.paulaburke.dsmovie.repositories.ScoreRepository;
import com.paulaburke.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		User user = userRepository.findByEmail(dto.getEmail());
		if (user == null) {
			user = new User();
			user.setEmail(dto.getEmail());
			user = userRepository.saveAndFlush(user);
			
		}
		
		Movie movie = movieRepository.findById(dto.getMovieId()).get();
		
		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		for (Score s : movie.getScores()) {
			sum = sum + s.getValue();
			
		}
		
		double avg = sum / movie.getScore().SIZE;
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
		
		
	}

}
