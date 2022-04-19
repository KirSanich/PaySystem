package com.example.paysystem.controller;


import com.example.paysystem.dto.flat.FlatDtoRequest;
import com.example.paysystem.dto.flat.FlatDtoResponse;
import com.example.paysystem.entity.Flat;
import com.example.paysystem.mapper.flat.FlatMapper;
import com.example.paysystem.service.flat.FlatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flats")
@AllArgsConstructor
public class FlatController {

    @Autowired
    private final FlatService flatService;

    @Autowired
    private final FlatMapper flatMapper;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {

        List<FlatDtoResponse> flatsList = flatService.getAllFlats()
                .stream()
                .map(flatMapper::fromFlatToDtoResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(flatsList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createFlat(@RequestBody FlatDtoRequest flatDtoRequest, Authentication authentication) {

        Flat flat = flatMapper.fromFlatDtoRequestForCreateToFlat(flatDtoRequest);
        flatService.createFlat(flat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
