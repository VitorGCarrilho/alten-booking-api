package com.alten.bookingservice.repository;

import com.alten.bookingservice.entity.BookingDayEntity;
import com.alten.bookingservice.exception.AlreadyBookedException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingDayRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookingDayRepository.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BookingDayJpaRepository bookingDayJpaRepository;

    @Transactional
    public void saveAll(List<BookingDayEntity> bookingDays) {
        for (var bookingDay: bookingDays) {
            if (this.isNew(bookingDay)) {
                entityManager.persist(bookingDay);
            } else {
                throw new AlreadyBookedException("Room already Booked");
            }
        }

        try {
            entityManager.flush();
        } catch (PersistenceException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                logger.info("method=saveAll status=Constraint validation exception when trying to commit transaction"
                        , e.getCause());
                throw new AlreadyBookedException(e.getCause());
            }
            throw e;
        }
    }

    public List<BookingDayEntity> findByRoomNumberAndBookingDateBetween(int roomNumber, LocalDate from, LocalDate until) {
        return this.bookingDayJpaRepository.findByBookId_RoomNumberAndBookId_BookingDateBetween(roomNumber, from, until);
    }

    private boolean isNew(BookingDayEntity bookingDay) {
        var booking = entityManager.find(BookingDayEntity.class, bookingDay.getBookId());
        return booking == null;
    }

    @Transactional
    public void updateBooking(String id, List<BookingDayEntity> bookingDays) {
        bookingDayJpaRepository.deleteByBooking_id(id);
        this.saveAll(bookingDays);
    }

    @Transactional
    public void removeBooking(String id) {
        bookingDayJpaRepository.deleteByBooking_id(id);
    }
}
