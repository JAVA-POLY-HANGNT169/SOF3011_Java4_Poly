package com.poly.hangnt169.service;

import com.poly.hangnt169.entity.Lop;
import com.poly.hangnt169.repository.LopRepository;

import java.util.List;

/**
 * @author hangnt169
 */
public class LopService {

    private LopRepository lopRepository = new LopRepository();

    public List<Lop>getAll(){
        return lopRepository.getAll();
    }
}
