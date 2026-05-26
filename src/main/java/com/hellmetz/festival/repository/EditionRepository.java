package com.hellmetz.festival.repository;

import com.hellmetz.festival.model.Artiste;
import com.hellmetz.festival.model.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long>{
}
