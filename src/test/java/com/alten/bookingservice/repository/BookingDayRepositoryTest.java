package com.alten.bookingservice.repository;

import com.alten.bookingservice.entity.BookingDayEntity;
import com.alten.bookingservice.exception.AlreadyBookedException;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingDayRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private BookingDayRepository bookingDayRepository;

    @Test
    public void shouldSaveTest() {
        // GIVEN
        var bookingDayEntity = SampleFactoryUtils.bookingDayEntity();
        var bookingDays = Arrays.asList(bookingDayEntity);

        Mockito.when(entityManager.find(BookingDayEntity.class, bookingDayEntity.getBookId())).thenReturn(null);


        // WHEN
        bookingDayRepository.saveAll(bookingDays);

        // THEN
        Mockito.verify(entityManager).persist(bookingDayEntity);
        Mockito.verify(entityManager).flush();
    }

    @Test
    public void shouldNotSaveWhenAlreadyExistTest() {
        // GIVEN
        var bookingDayEntity = SampleFactoryUtils.bookingDayEntity();
        var bookingDays = Arrays.asList(bookingDayEntity);

        Mockito.when(entityManager.find(BookingDayEntity.class, bookingDayEntity.getBookId()))
                .thenReturn(bookingDayEntity);

        // WHEN
        assertThrows(AlreadyBookedException.class, () -> {
            bookingDayRepository.saveAll(bookingDays);
        });

        // THEN
        Mockito.verify(entityManager, Mockito.times(0)).persist(bookingDayEntity);
    }

    @Test
    public void shouldThrowAlreadyBookedExceptionWhenTheCommitTransactionThrowsConstraintViolationException() {
        // GIVEN
        var bookingDayEntity = SampleFactoryUtils.bookingDayEntity();
        var bookingDays = Arrays.asList(bookingDayEntity);

        Mockito.when(entityManager.find(BookingDayEntity.class, bookingDayEntity.getBookId()))
                .thenReturn(null);
        Mockito.doThrow(new PersistenceException(new ConstraintViolationException("", null, ""))).when(entityManager).flush();

        // WHEN
        assertThrows(AlreadyBookedException.class, () -> {
            bookingDayRepository.saveAll(bookingDays);
        });
    }

    @Test
    public void shouldNotThrowAlreadyBookedExceptionWhenTheCommitTransactionThrowsAnyOtherDatabaseIssue() {
        // GIVEN
        var bookingDayEntity = SampleFactoryUtils.bookingDayEntity();
        var bookingDays = Arrays.asList(bookingDayEntity);

        Mockito.when(entityManager.find(BookingDayEntity.class, bookingDayEntity.getBookId()))
                .thenReturn(null);
        Mockito.doThrow(new PersistenceException()).when(entityManager)
                .flush();

        // WHEN
        assertThrows(PersistenceException.class, () -> {
            bookingDayRepository.saveAll(bookingDays);
        });
    }

}