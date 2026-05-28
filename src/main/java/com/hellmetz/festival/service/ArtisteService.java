package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Artiste;
import com.hellmetz.festival.repository.ArtisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ArtisteService {

    @Autowired
    private ArtisteRepository artisteRepository;

    public List<Artiste> findAll() {
        return artisteRepository.findAllByOrderByNomSceneAsc();
    }

    public Artiste findById(Long id) {return artisteRepository.findById(id).orElseThrow(() -> new RuntimeException("Artiste introuvable"));}

    public List<Artiste> findByGroupeId(int idGroupe) {
        return artisteRepository.findByGroupeId(idGroupe);
    }

    public void save(Artiste artiste) {
        artisteRepository.save(artiste);
    }

    public void deleteById(Long id) {
        artisteRepository.deleteById(id);
    }

    public void delete(Artiste artiste) {
        artisteRepository.delete(artiste);
    }

}
