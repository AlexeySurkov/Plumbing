package ru.company.data;

import org.springframework.data.repository.CrudRepository;

import ru.company.classes.SanitaryWare;

public interface SanitaryWareRepository
        extends CrudRepository<SanitaryWare, String> {
}
