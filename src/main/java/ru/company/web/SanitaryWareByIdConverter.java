package ru.company.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.company.classes.SanitaryWare;
import ru.company.data.SanitaryWareRepository;

@Component
public class SanitaryWareByIdConverter implements Converter<String, SanitaryWare> {

    private SanitaryWareRepository sanitaryWareRepository;

    @Autowired
    public SanitaryWareByIdConverter(SanitaryWareRepository sanitaryWareRepository) {
        this.sanitaryWareRepository = sanitaryWareRepository;
    }

    @Override
    public SanitaryWare convert(String id) {
        Optional<SanitaryWare> optionalIngredient = sanitaryWareRepository.findById(id);

        return optionalIngredient.isPresent() ?
                optionalIngredient.get() : null;
    }
}
