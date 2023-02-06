package com.example.SpringData;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BikeRepositoryInterface extends CrudRepository<Bike, Long> {

    @Override
    <S extends Bike> S save(S entity);

    @Override
    Optional<Bike> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    boolean existsById(Long aLong);
}
