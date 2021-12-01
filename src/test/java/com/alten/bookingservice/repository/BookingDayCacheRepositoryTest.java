package com.alten.bookingservice.repository;

import com.alten.bookingservice.dto.response.AvailabilityResponseDTO;
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

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BookingDayCacheRepositoryTest {

    @Mock
    private RedisTemplate<String, List<AvailabilityResponseDTO>> redisTemplate;

    @Mock
    private ValueOperations<String,  List<AvailabilityResponseDTO>> valueOperations;

    @InjectMocks
    private BookingDayCacheRepository cacheRepository;

    @Test
    public void getCache() {
        var room = 1;
        var from = LocalDate.of(2021, 1, 1);
        var until = LocalDate.of(2021, 1, 2);
        var id = "1:2021-01-01:2021-01-02";
        List<AvailabilityResponseDTO> availabilityResponseDTOS = SampleFactoryUtils.availabilityResponseDTOS();

        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(valueOperations.get(id)).thenReturn(availabilityResponseDTOS);

        var response = cacheRepository.getAvailability(room, from, until);
        assertEquals(availabilityResponseDTOS, response);
    }

    @Test
    public void saveCache() {
        var room = 1;
        var from = LocalDate.of(2021, 1, 1);
        var until = LocalDate.of(2021, 1, 2);
        var id = "1:2021-01-01:2021-01-02";
        List<AvailabilityResponseDTO> availabilityResponseDTOS = SampleFactoryUtils.availabilityResponseDTOS();

        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        cacheRepository.saveAvailability(room, from, until, availabilityResponseDTOS);

        Mockito.verify(valueOperations).set(id, availabilityResponseDTOS);
    }

}