package com.project.university.mapper;

import com.project.university.model.Department;
import com.project.university.model.DepartmentType;
import com.project.university.model.Lector;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DepartmentMapper extends BaseMapper<Department> {

    @Override
    public Department updateMap(Department source, Department target) {
        return target
            .setType((DepartmentType) getNotNull(source.getType(), target.getType()))
            .setHead((Lector) getNotNull(source.getHead(), target.getHead()))
            .setLectors((List<Lector>) getNotNull(source.getLectors(), target.getLectors()));
    }
}
