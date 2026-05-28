package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Concert;
import com.hellmetz.festival.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    public List<Concert> findAll() {return concertRepository.findAllByOrderByNomGroupeAsc();}

    public Concert findById(Long id) {return concertRepository.findById(id).orElseThrow(() -> new RuntimeException("Concert introuvable"));}

    public void save(Concert groupe) {
        concertRepository.save(groupe);
    }

    public void delete(Concert groupe) {
        concertRepository.delete(groupe);
    }

    public void deleteById(Long id) {
        concertRepository.deleteById(id);
    }
}