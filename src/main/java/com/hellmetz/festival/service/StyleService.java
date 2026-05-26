package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Style;
import com.hellmetz.festival.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class StyleService {

    @Autowired
    private StyleRepository styleRepository;

    // remplace findAll() du DAO
    public List<Style> findAll() {
        return styleRepository.findAll();
    }
}
