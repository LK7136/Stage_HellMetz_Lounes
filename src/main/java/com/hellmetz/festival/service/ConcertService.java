package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Concert;
import com.hellmetz.festival.model.Groupe;
import com.hellmetz.festival.repository.ConcertRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConcertService {

    private final ConcertRepository concertRepository;
    private static final Sort TRI = Sort.by("id").ascending();

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    @Transactional(readOnly = true)
    public Page<Concert> findPage(int page, int taille) {
        return concertRepository.findAll(PageRequest.of(page, taille, TRI));
    }

    @Transactional(readOnly = true)
    public Page<Concert> findTout() {
        return concertRepository.findAll(Pageable.unpaged(TRI));
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
