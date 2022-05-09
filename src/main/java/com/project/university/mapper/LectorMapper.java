package com.project.university.mapper;

import com.project.university.model.Degree;
import com.project.university.model.Department;
import com.project.university.model.Lector;
import org.springframework.stereotype.Component;
import java.math.BigInteger;
import java.util.List;

@Component
public class LectorMapper extends BaseMapper<Lector> {

    @Override
    public Lector updateMap(Lector source, Lector target) {
        return target
            .setFirstName((String) getNotNull(source.getFirstName(), target.getFirstName()))
            .setLastName((String) getNotNull(source.getLastName(), target.getLastName()))
            .setSalary((BigInteger) getNotNull(source.getSalary(), target.getSalary()))
            .setDegree((Degree) getNotNull(source.getDegree(), target.getDegree()))
            .setDepartments((List<Department>) getNotNull(source.getDepartments(), target.getDepartments()));
    }
}
