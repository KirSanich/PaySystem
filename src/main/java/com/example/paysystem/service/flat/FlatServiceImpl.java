package com.example.paysystem.service.flat;

import com.example.paysystem.entity.Flat;
import com.example.paysystem.exception.flat.FlatNotEnableForBooking;
import com.example.paysystem.exception.flat.FlatWithCurrentIdNotFound;
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

    @Override
    public Flat findFlatById(Long id) {
        log.info("Finding flat with id:{}", id);
        return flatRepository.findById(id).orElseThrow(() -> new FlatWithCurrentIdNotFound("No found flat with id:" + id));
    }

    @Override
    public boolean isFlatEnabled(Long id) {
       Flat flat = findFlatById(id);
       return flat.isBooked();
    }
}
