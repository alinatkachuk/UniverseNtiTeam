package com.alinatkachuk.universeNtiTeam.service;

import com.alinatkachuk.universeNtiTeam.exception.ResourceNotFoundException;
import com.alinatkachuk.universeNtiTeam.model.Lord;
import com.alinatkachuk.universeNtiTeam.repository.LordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class LordServiceImpl implements LordService {

    LordRepository lordRepository;

    @Autowired
    public LordServiceImpl (LordRepository lordRepository) {
        this.lordRepository = lordRepository;
    }

    @Override
    public Lord saveLord(Lord lord) {
        return lordRepository.save(lord);
    }

    @Override
    public Lord editLord(Long lordId, Lord lord) {
        return lordRepository.findById (lordId).map(updatedLord -> {
            updatedLord.setPlanets (lord.getPlanets ());
            return lordRepository.save(updatedLord);
        }).orElseThrow (() -> new ResourceNotFoundException ("LordId " + lordId + " not found"));
    }
}
