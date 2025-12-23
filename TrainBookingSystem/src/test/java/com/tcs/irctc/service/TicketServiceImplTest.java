package com.tcs.irctc.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.tcs.irctc.dto.TicketRequestDTO;
import com.tcs.irctc.entity.Ticket;
import com.tcs.irctc.entity.Train;
import com.tcs.irctc.repository.TicketRepository;
import com.tcs.irctc.repository.TrainRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    TicketRepository ticketRepository;

    @Mock
    TrainRepository trainRepository;

    @InjectMocks
    TicketServiceImpl service;

    Train train;

    @BeforeEach
    void setup() {
        train = new Train();
        train.setTrainId(1L);
        train.setSource("Mumbai");
        train.setDestination("Delhi");
        train.setAcFare(2000.0);
        train.setNonAcFare(1500.0);
        train.setSleeperFare(1200.0);
        train.setAvailableSeats(10);
    }

    @Test
    void testFareCalculation_Normal_AC() {
        TicketRequestDTO req = baseReq();
        req.setSeatType("AC");
        req.setBookingType("NORMAL");

        when(trainRepository.findPlain(1L)).thenReturn(train);
        when(ticketRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(trainRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        var dto = service.book(req);
        assertEquals(2000.0, dto.getFare());
        assertTrue(dto.getHighFareTicket());
    }

    @Test
    void testTatkalCharge_25percent() {
        TicketRequestDTO req = baseReq();
        req.setSeatType("SLEEPER");
        req.setBookingType("TATKAL"); // base 1200 -> 1500

        when(trainRepository.findPlain(1L)).thenReturn(train);
        when(ticketRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(trainRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        var dto = service.book(req);
        assertEquals(1500.0, dto.getFare());
        assertFalse(dto.getHighFareTicket());
    }

    @Test
    void testSeatDecrementOnBooking() {
        TicketRequestDTO req = baseReq();

        when(trainRepository.findPlain(1L)).thenReturn(train);
        when(ticketRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(trainRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        service.book(req);
        verify(trainRepository, times(1)).save(argThat(t -> t.getAvailableSeats() == 9));
    }

    @Test
    void testSoftDeleteCancellationIncrementsSeat() {
        Ticket t = new Ticket();
        t.setId(99L);
        t.setTrainId(1L);
        t.setStatus("BOOKED");
        t.setIsDeleted(false);

        when(ticketRepository.findById(99L)).thenReturn(java.util.Optional.of(t));
        when(trainRepository.findPlain(1L)).thenReturn(train);

        service.cancel(99L);
        assertTrue(t.getIsDeleted());
        assertEquals("CANCELLED", t.getStatus());
        verify(trainRepository).save(argThat(tr -> tr.getAvailableSeats() == 11));
    }

    private TicketRequestDTO baseReq() {
        TicketRequestDTO req = new TicketRequestDTO();
        req.setPassengerName("Test User");
        req.setEmail("test@example.com");
        req.setTrainId(1L);
        req.setSource("Mumbai");
        req.setDestination("Delhi");
        req.setSeatType("AC");
        req.setBookingType("NORMAL");
        req.setTravelDate(LocalDate.now().plusDays(1));
        return req;
    }
}

