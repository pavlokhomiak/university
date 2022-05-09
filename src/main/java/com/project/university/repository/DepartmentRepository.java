package com.project.university.repository;

import com.project.university.model.Department;
import com.project.university.model.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findDepartmentByType(DepartmentType type);

}
