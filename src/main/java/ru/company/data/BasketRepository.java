package ru.company.data;

import org.springframework.data.repository.CrudRepository;

import ru.company.classes.Basket;

public interface BasketRepository
         extends CrudRepository<Basket, Long> {
}
