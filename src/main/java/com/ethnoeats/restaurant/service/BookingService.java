package com.ethnoeats.restaurant.service;

import com.ethnoeats.restaurant.model.Booking;
import com.ethnoeats.restaurant.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        // Validate the booking
        validateBooking(booking);

        // Check availability
        if (!isAvailable(booking.getDate(), booking.getTime(), booking.getPartySize())) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.CONFLICT,
                    "The requested time slot is not available."
            );
        }

        // If available, save the booking to the database
        return bookingRepository.save(booking);
    }

    private void validateBooking(Booking booking) {
        LocalDateTime bookingDateTime = booking.getDate().atTime(booking.getTime());
        LocalDateTime now = LocalDateTime.now();

        // Check if the booking is in the future
        if (bookingDateTime.isBefore(now)) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Booking date and time must be in the future."
            );
        }

        // Check if the party size is valid (e.g., not negative, within restaurant capacity)
        if (booking.getPartySize() <=  0 || booking.getPartySize() > MAX_PARTY_SIZE) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Invalid party size."
            );
        }

        // Additional validation checks can be added here
    }

    private boolean isAvailable(LocalDate date, LocalTime time, int partySize) {
        // Query the database for existing bookings at the requested date and time
        List<Booking> existingBookings = bookingRepository.findByDateAndTime(date, time);

        // Check if there is enough capacity for the requested party size
        int totalCapacity = existingBookings.stream()
                .mapToInt(Booking::getPartySize)
                .sum();

        return totalCapacity + partySize <= MAX_CAPACITY;
    }

    // Constants for maximum party size and restaurant capacity
    private static final int MAX_PARTY_SIZE =  10;
    private static final int MAX_CAPACITY =  50;
}
