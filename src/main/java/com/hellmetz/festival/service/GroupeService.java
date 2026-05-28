package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Groupe;
import com.hellmetz.festival.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupeService {

    @Autowired
    private GroupeRepository groupeRepository;

    public List<Groupe> findAll() {return groupeRepository.findAllByOrderByNomGroupeAsc();}

    public Groupe findById(Long id) {return groupeRepository.findById(id).orElseThrow(() -> new RuntimeException("Groupe introuvable"));}

    public void save(Groupe groupe) {
        groupeRepository.save(groupe);
    }

    public void delete(Groupe groupe) {
        groupeRepository.delete(groupe);
    }

    public void deleteById(Long id) {
        groupeRepository.deleteById(id);
    }
}