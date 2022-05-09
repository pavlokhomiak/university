package com.project.university.service;

import com.project.university.model.Department;
import com.project.university.model.DepartmentType;
import java.util.Optional;

public interface DepartmentService extends BaseService<Department> {

    Optional<Department> findDepartmentByType(DepartmentType type);
}
