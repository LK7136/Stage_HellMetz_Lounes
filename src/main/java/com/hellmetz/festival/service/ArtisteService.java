package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Artiste;
import com.hellmetz.festival.repository.ArtisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service          // Déclare cette classe comme Bean Spring de type Service
@Transactional    // Gère automatiquement les transactions base de données

public class ArtisteService {

    @Autowired  // Spring injecte automatiquement le Repository
    private ArtisteRepository artisteRepository;

    // Récupère tous les artiste
    public List<Artiste> findAll() {
        return artisteRepository.findAllByOrderByNomSceneAsc();
    }

    // Récupère un artiste par son ID
    public Artiste findById(Long id) {
        // Optional.orElseThrow lève une exception si l'entité n'existe pas
        return artisteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artiste introuvable"));
    }

    // Récupère la liste des artistes par leurs id_groupe (liste les artistes d'un meme groupe)
    public List<Artiste> findByGroupeId(int idGroupe) {
        return artisteRepository.findByGroupeId(idGroupe);
    }

    // Met à jour un artiste existant & Insère un nouvel artiste
    public void save(Artiste artiste) {
        artisteRepository.save(artiste);
    }

    // Supprime un artiste via son ID
    public void deleteById(Long id) {
        artisteRepository.deleteById(id);
    }

    // Supprime un artiste objet
    public void delete(Artiste artiste) {
        artisteRepository.delete(artiste);
    }

}
