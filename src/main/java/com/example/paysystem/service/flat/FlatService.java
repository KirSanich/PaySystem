package com.example.paysystem.service.flat;

import com.example.paysystem.entity.Flat;

import java.util.List;

public interface FlatService {

    List<Flat> getAllFlats();

    void createFlat(Flat flat);

    Flat findFlatById(Long id);

    boolean isFlatEnabled(Long id);
}
