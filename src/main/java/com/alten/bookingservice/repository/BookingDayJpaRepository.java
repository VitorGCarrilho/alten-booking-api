package com.alten.bookingservice.repository;

import com.alten.bookingservice.entity.BookingDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

interface BookingDayJpaRepository extends JpaRepository<BookingDayEntity, String> {

    List<BookingDayEntity> findByBookId_RoomNumberAndBookId_BookingDateBetween(int roomNumber, LocalDate from, LocalDate until);
    void deleteByBooking_id(String id);
}
