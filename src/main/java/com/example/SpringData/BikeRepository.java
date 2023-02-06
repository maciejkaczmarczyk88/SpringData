package com.example.SpringData;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
class BikeRepository implements BikeRepositoryInterface {
    private final EntityManager entityManager;

    public BikeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Bike save(Bike bike) {
        if (exists(bike)) {
            entityManager.merge(bike);
        } else {
            entityManager.persist(bike);
        }
        return bike;
    }

    @Override
    public <S extends Bike> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    public Optional<Bike> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Bike.class, id));
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @Override
    public void delete(Bike entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Bike> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Bike> findAll() {
        return null;
    }

    @Override
    public Iterable<Bike> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    private boolean exists(Bike bike) {
        return entityManager.find(Bike.class, bike.getId()) != null;
    }
}