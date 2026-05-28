package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Scene;
import com.hellmetz.festival.repository.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SceneService {

    @Autowired
    private SceneRepository sceneRepository;

    public List<Scene> findAll() {return sceneRepository.findAllByOrderByNomAsc();}

    public Scene findById(Long id) {return sceneRepository.findById(id).orElseThrow(() -> new RuntimeException("Scene introuvable"));}

    public void save(Scene scene) {
        sceneRepository.save(scene);
    }

    public void delete(Scene scene) {
        sceneRepository.delete(scene);
    }

    public void deleteById(Long id) {
        sceneRepository.deleteById(id);
    }
}