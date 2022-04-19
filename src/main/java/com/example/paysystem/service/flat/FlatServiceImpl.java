package com.example.paysystem.service.flat;

import com.example.paysystem.entity.Flat;
import com.example.paysystem.repository.FlatRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FlatServiceImpl implements FlatService {

    @Autowired
    private final FlatRepository flatRepository;

    @Override
    public List<Flat> getAllFlats() {
        log.info("Getting all flats");
        return flatRepository.findAll();
    }

    @Override
    public void createFlat(Flat flat) {
        log.info("Creating flat");
        flatRepository.save(flat);
    }
}
