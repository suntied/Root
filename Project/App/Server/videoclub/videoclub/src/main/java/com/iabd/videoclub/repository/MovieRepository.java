package com.iabd.videoclub.repository;

import com.iabd.videoclub.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository  extends JpaRepository<Movie, Long> {
}
