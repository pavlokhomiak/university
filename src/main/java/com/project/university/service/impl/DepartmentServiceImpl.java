package com.project.university.service.impl;

import com.project.university.mapper.DepartmentMapper;
import com.project.university.model.Department;
import com.project.university.model.DepartmentType;
import com.project.university.repository.DepartmentRepository;
import com.project.university.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Department save(Department entity) {
        return departmentRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Department> update(Integer id, Department source) {
        return departmentRepository.findById(id).map(target -> update(source, target));
    }

    private Department update(Department source, Department target) {
        Department updateResult = mapper.updateMap(source, target);
        return departmentRepository.save(updateResult);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findDepartmentByType(DepartmentType type) {
        return departmentRepository.findDepartmentByType(type);
    }
}
