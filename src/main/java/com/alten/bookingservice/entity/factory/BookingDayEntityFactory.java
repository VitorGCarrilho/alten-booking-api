package com.alten.bookingservice.entity.factory;

import com.alten.bookingservice.entity.BookingDayEntity;
import com.alten.bookingservice.entity.BookingEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingDayEntityFactory {

    public List<BookingDayEntity> getBookingDays(BookingEntity bookingEntity) {
        var bookingDays = new ArrayList<BookingDayEntity>();
        var current = bookingEntity.getFromDate();
        while (!current.isAfter(bookingEntity.getUntilDate())) {
            var bookingDay = new BookingDayEntity(bookingEntity, current);
            bookingDays.add(bookingDay);
            current = current.plusDays(1);
        }
        return bookingDays;
    }
}
