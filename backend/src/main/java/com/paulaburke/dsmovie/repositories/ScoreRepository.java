package com.paulaburke.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulaburke.dsmovie.entities.Score;
import com.paulaburke.dsmovie.entities.ScorePK;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {
	
}
