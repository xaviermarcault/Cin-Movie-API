package com.api.cinemovie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cinemovie.model.Movie;

@Repository
public interface MovieDao extends JpaRepository<Movie,Integer>{

	public Movie findById(int id);
}
