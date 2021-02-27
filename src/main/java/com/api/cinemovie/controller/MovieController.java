package com.api.cinemovie.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.cinemovie.dao.MovieDao;
import com.api.cinemovie.model.Movie;

@RestController
public class MovieController {

	@Autowired
	private MovieDao movieDao;

	// Récupérer une liste de films
	@GetMapping(value = "/films")
	public List<Movie> listeFilms() {
		return movieDao.findAll();
	}

	// Récupérer un film avec id
	@GetMapping(value = "/films/{id}")
	public Movie afficherUnFilm(@PathVariable int id) {
		return movieDao.findById(id);
	}

	// Ajouter un film
	@PostMapping(value = "/films")
	public ResponseEntity<Void> ajouterFilm(@RequestBody Movie movie) {
		Movie movieAdded = movieDao.save(movie);
		
		if (movieAdded == null) {
			ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(movie.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	// Supprimer un film
	@DeleteMapping(value = "/films/{id}")
	public void supprimerFilm(@PathVariable int id) {
		movieDao.deleteById(id);
	}
}
