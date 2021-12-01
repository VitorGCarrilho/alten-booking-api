package com.alten.bookingservice.repository;

import com.alten.bookingservice.dto.response.BookingResponseDTO;
import com.alten.bookingservice.utils.SampleFactoryUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingCacheRepositoryTest {

    @Mock
    private RedisTemplate<String, BookingResponseDTO> redisTemplate;

    @Mock
    private ValueOperations<String, BookingResponseDTO> valueOperations;

    @InjectMocks
    private BookingCacheRepository cacheRepository;

    @Test
    public void getCache() {
        var id = "123";
        var booking = SampleFactoryUtils.bookingResponseDTO();

        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(valueOperations.get(id)).thenReturn(booking);

        var response = cacheRepository.getBooking(id);
        assertEquals(booking, response);
    }

    @Test
    public void saveCache() {
        var id = "123";
        var booking = SampleFactoryUtils.bookingResponseDTO();

        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        cacheRepository.saveBooking(booking);

        Mockito.verify(valueOperations).set(booking.getId(), booking);
    }

}