package com.hellmetz.festival.repository;

import com.hellmetz.festival.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
// JpaRepository<T, ID> : T = votre entité, ID = type de sa clé primaire
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    List<Concert> findAllByOrderByDateHeureDebutAsc();
}
