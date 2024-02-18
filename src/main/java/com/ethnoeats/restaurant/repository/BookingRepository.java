package com.ethnoeats.restaurant.repository;

import com.ethnoeats.restaurant.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByDateAndTime(LocalDate date, LocalTime time);
}
