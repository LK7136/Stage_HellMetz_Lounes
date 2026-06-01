package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Groupe;
import com.hellmetz.festival.repository.GroupeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupeService {

    private final GroupeRepository groupeRepository;


    private static final Sort TRI = Sort.by("nomGroupe").ascending();

    public GroupeService(GroupeRepository groupeRepository) {
        this.groupeRepository = groupeRepository;
    }


    @Transactional(readOnly = true)
    public Page<Groupe> findPage(int page, int taille) {
        return groupeRepository.findAll(PageRequest.of(page, taille, TRI));
    }

    @Transactional(readOnly = true)
    public Page<Groupe> findTout() {
        return groupeRepository.findAll(Pageable.unpaged(TRI));
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
