package com.alten.bookingservice.entity.factory;

import com.alten.bookingservice.entity.BookingDayEntity;
import com.alten.bookingservice.entity.BookingEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingDayEntityFactory {

    public List<BookingDayEntity> getBookingDays(BookingEntity bookingEntity) {
        var bookingDays = new ArrayList<BookingDayEntity>();

        for (LocalDate current = bookingEntity.getFromDate(); !current.isAfter(bookingEntity.getUntilDate()); current = current.plusDays(1)) {
            var bookingDay = new BookingDayEntity(bookingEntity, current);
            bookingDays.add(bookingDay);
        }
        return bookingDays;
    }
}
