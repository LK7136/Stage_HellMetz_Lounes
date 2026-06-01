package com.hellmetz.festival.repository;

import com.hellmetz.festival.model.Concert;
import com.hellmetz.festival.model.Edition;
import com.hellmetz.festival.model.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    // tous les concerts d'une journée donnée
    List<Concert> findByDateHeureDebutBetween(LocalDateTime debut, LocalDateTime fin);

    // concerts d'une édition (pour la progression)
    List<Concert> findByEdition(Edition edition);

    List<Concert> findAllByOrderByIdAsc();
}
