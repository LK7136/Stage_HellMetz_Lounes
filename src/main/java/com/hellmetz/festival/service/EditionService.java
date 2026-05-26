package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Edition;
import com.hellmetz.festival.model.Style;
import com.hellmetz.festival.repository.EditionRepository;
import com.hellmetz.festival.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EditionService {

    @Autowired
    private EditionRepository editionRepository;


    public List<Edition> findAll() {return editionRepository.findAll();}
}
