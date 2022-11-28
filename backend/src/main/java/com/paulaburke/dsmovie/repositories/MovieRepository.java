package com.paulaburke.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulaburke.dsmovie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
}
