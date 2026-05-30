package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Groupe;
import com.hellmetz.festival.repository.GroupeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupeService {

    private final GroupeRepository groupeRepository;

    public GroupeService(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }

    @Transactional(readOnly = true)
    public List<Groupe> findAll() {
        return groupeRepository.findAllByOrderByNomGroupeAsc();
    }

    @Transactional(readOnly = true)
    public Groupe findById(Long id) {
        return groupeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Groupe introuvable : " + id));
    }

    public void save(Groupe groupe) {
        groupeRepository.save(groupe);
    }

    public void deleteById(Long id) {
        groupeRepository.deleteById(id);
    }
}
