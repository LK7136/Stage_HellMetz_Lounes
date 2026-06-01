package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Concert;
import com.hellmetz.festival.repository.ConcertRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConcertService {

    private final ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    @Transactional(readOnly = true)
    public List<Concert> findAll() {
        return concertRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Concert findById(Long id) {
        return concertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concert introuvable : " + id));
    }

    public void save(Concert concert) {
        concertRepository.save(concert);
    }

    public void deleteById(Long id) {
        concertRepository.deleteById(id);
    }
}
