package com.example.SpringData;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BikeService {

    BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public void add(NewBikeDto newBike) {
        Bike bike = new Bike(newBike.getId(), newBike.getModel(), newBike.getSerialNo(), newBike.getHourPrice()
                , newBike.getDayPrice(), newBike.getModel());
        bikeRepository.save(bike);
    }

    public void deleteById(Long bikeId) {
        bikeRepository.deleteById(bikeId);
    }

    public double rentForHours(Long bikeId, int hours, String borrowerId) {
        LocalDateTime dateOfReturn = LocalDateTime.now().plusHours(hours);
        double hourPrice = updateBike(bikeId, dateOfReturn, borrowerId).getHourPrice();
        return hourPrice * hours;
    }

    public double rentForDay(Long bikeId, String borrowerId) {
        LocalDateTime dateOfReturn = LocalDateTime.now().withHour(23).withMinute(59);
        return updateBike(bikeId, dateOfReturn, borrowerId).getDayPrice();
    }

    public void returnBike(Long bikeId) {
        updateBike(bikeId, null, null);
    }

    private Bike updateBike(Long bikeId, LocalDateTime dateOfReturn, String borrowerId) {
        Bike bike = bikeRepository
                .findById(bikeId)
                .orElseThrow(BikeNotFoundException::new);
        bike.setLocalDateTime(dateOfReturn);
        bike.setBorrowerId(borrowerId);
        bikeRepository.save(bike);
        return bike;
    }
}
