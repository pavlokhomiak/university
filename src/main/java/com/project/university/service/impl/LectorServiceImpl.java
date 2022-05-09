package com.project.university.service.impl;

import com.project.university.mapper.LectorMapper;
import com.project.university.model.Lector;
import com.project.university.repository.LectorRepository;
import com.project.university.service.LectorService;
import lombok.RequiredArgsConstructor;;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectorServiceImpl implements LectorService {

    private final LectorRepository lectorRepository;
    private final LectorMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<Lector> findAll() {
        return lectorRepository.findAll();
    }

    @Override
    @Transactional
    public Lector save(Lector entity) {
        return lectorRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Lector> findById(Integer id) {
        return lectorRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Lector> update(Integer id, Lector source) {
        return lectorRepository.findById(id).map(target -> update(source, target));
    }

    private Lector update(Lector source, Lector target) {
        Lector updateResult = mapper.updateMap(source, target);
        return lectorRepository.save(updateResult);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        lectorRepository.deleteById(id);
    }

    @Override
    public List<Lector> findLectorsByFirstNameContainingAndLastNameContaining(String template) {
        return lectorRepository.findLectorsByFirstNameContainingAndLastNameContaining(template);
    }
}
