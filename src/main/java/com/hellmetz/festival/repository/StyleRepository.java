package com.hellmetz.festival.repository;

import com.hellmetz.festival.model.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    //vide car spring fait automatiqement
}