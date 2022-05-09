package com.project.university.service;

import com.project.university.model.Lector;
import java.util.List;

public interface LectorService extends BaseService<Lector> {

    List<Lector> findLectorsByFirstNameContainingAndLastNameContaining(String template);
}
