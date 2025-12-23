package com.tcs.irctc.service;


import com.tcs.irctc.dto.TicketRequestDTO;
import com.tcs.irctc.dto.TicketResponseDTO;
import com.tcs.irctc.entity.Ticket;
import com.tcs.irctc.entity.Train;
import com.tcs.irctc.exception.InvalidOperationException;
import com.tcs.irctc.exception.ResourceNotFoundException;
import com.tcs.irctc.repository.TicketRepository;
import com.tcs.irctc.repository.TrainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TrainRepository trainRepository;

    private static final Set<String> VALID_SEATS = Set.of("AC", "NON-AC", "SLEEPER");
    private static final Set<String> VALID_BOOKINGS = Set.of("NORMAL", "TATKAL");

    public TicketServiceImpl(TicketRepository ticketRepository, TrainRepository trainRepository) {
        this.ticketRepository = ticketRepository;
        this.trainRepository = trainRepository;
    }

    @Override
    @Transactional
    public TicketResponseDTO book(TicketRequestDTO req) {
        validateRequest(req);

        Train train = trainRepository.findPlain(req.getTrainId());
        if (train == null) throw new ResourceNotFoundException("Train not found: " + req.getTrainId());

        validateRoute(req, train);
        double baseFare = pickFare(train, normalize(req.getSeatType()));
        double fare = computeFare(baseFare, normalize(req.getBookingType()));

        if (train.getAvailableSeats() == null || train.getAvailableSeats() <= 0) {
            throw new InvalidOperationException("No seats available for train " + train.getTrainId());
        }

        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainRepository.save(train);

        Ticket ticket = new Ticket();
        ticket.setPassengerName(req.getPassengerName());
        ticket.setEmail(req.getEmail());
        ticket.setTrainId(train.getTrainId());
        ticket.setSource(req.getSource());
        ticket.setDestination(req.getDestination());
        ticket.setSeatType(normalize(req.getSeatType()));
        ticket.setBookingType(normalize(req.getBookingType()));
        ticket.setFare(fare);
        ticket.setTravelDate(req.getTravelDate());
        ticket.setStatus("BOOKED");
        ticket.setIsDeleted(false);
        ticket.setHighFareTicket(fare > 2000.0);

        ticket = ticketRepository.save(ticket);
        return toDTO(ticket);
    }

    @Override
    public List<TicketResponseDTO> getAll() {
        return ticketRepository.findByIsDeletedFalse()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public TicketResponseDTO getById(Long id) {
        Ticket t = ticketRepository.findById(id)
                .filter(ticket -> !Boolean.TRUE.equals(ticket.getIsDeleted()))
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found: " + id));
        return toDTO(t);
    }

    @Override
    @Transactional
    public TicketResponseDTO update(Long id, TicketRequestDTO req) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found: " + id));

        if (Boolean.TRUE.equals(existing.getIsDeleted())) {
            throw new InvalidOperationException("Cannot update a cancelled (deleted) ticket.");
        }
        if ("COMPLETED".equalsIgnoreCase(existing.getStatus())) {
            throw new InvalidOperationException("Cannot update a completed ticket.");
        }

        validateRequest(req);
        Train train = trainRepository.findPlain(req.getTrainId());
        if (train == null) throw new ResourceNotFoundException("Train not found: " + req.getTrainId());
        validateRoute(req, train);

        double baseFare = pickFare(train, normalize(req.getSeatType()));
        double fare = computeFare(baseFare, normalize(req.getBookingType()));

        existing.setPassengerName(req.getPassengerName());
        existing.setEmail(req.getEmail());
        existing.setTrainId(req.getTrainId());
        existing.setSource(req.getSource());
        existing.setDestination(req.getDestination());
        existing.setSeatType(normalize(req.getSeatType()));
        existing.setBookingType(normalize(req.getBookingType()));
        existing.setTravelDate(req.getTravelDate());
        existing.setFare(fare);
        existing.setHighFareTicket(fare > 2000.0);

        return toDTO(ticketRepository.save(existing));
    }

    @Override
    @Transactional
    public void cancel(Long id) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found: " + id));

        if ("COMPLETED".equalsIgnoreCase(existing.getStatus())) {
            throw new InvalidOperationException("Completed tickets cannot be cancelled.");
        }
        if (Boolean.TRUE.equals(existing.getIsDeleted())) {
            throw new InvalidOperationException("Ticket already cancelled.");
        }

        Train train = trainRepository.findPlain(existing.getTrainId());
        if (train == null) throw new ResourceNotFoundException("Train not found: " + existing.getTrainId());

        existing.setIsDeleted(true);
        existing.setStatus("CANCELLED");
        ticketRepository.save(existing);

        train.setAvailableSeats(train.getAvailableSeats() + 1);
        trainRepository.save(train);
    }

    @Override
    @Transactional
    public TicketResponseDTO restore(Long id) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found: " + id));

        if (!Boolean.TRUE.equals(existing.getIsDeleted())) {
            throw new InvalidOperationException("Ticket is not cancelled; cannot restore.");
        }

        Train train = trainRepository.findPlain(existing.getTrainId());
        if (train == null) throw new ResourceNotFoundException("Train not found: " + existing.getTrainId());
        if (train.getAvailableSeats() <= 0) {
            throw new InvalidOperationException("No seats available to restore ticket.");
        }

        existing.setIsDeleted(false);
        existing.setStatus("BOOKED");
        ticketRepository.save(existing);

        train.setAvailableSeats(train.getAvailableSeats() - 1);
        trainRepository.save(train);

        return toDTO(existing);
    }

    @Override
    public List<TicketResponseDTO> search(String passengerName, String email, Long trainId, LocalDate travelDate) {
        List<Ticket> result = new ArrayList<>();

        if (passengerName != null && !passengerName.isBlank()) {
            result.addAll(ticketRepository.findByPassengerNameContainingIgnoreCaseAndIsDeletedFalse(passengerName));
        }
        if (email != null && !email.isBlank()) {
            result.addAll(ticketRepository.findByEmailIgnoreCaseAndIsDeletedFalse(email));
        }
        if (trainId != null) {
            result.addAll(ticketRepository.findByTrainIdAndIsDeletedFalse(trainId));
        }
        if (travelDate != null) {
            result.addAll(ticketRepository.findByTravelDateAndIsDeletedFalse(travelDate));
        }

        Map<Long, Ticket> map = new LinkedHashMap<>();
        for (Ticket t : result) map.put(t.getId(), t);

        return map.values().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TicketResponseDTO> highFare() {
        return ticketRepository.findByHighFareTicketTrueAndIsDeletedFalse()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TicketResponseDTO> byTravelDate(LocalDate date) {
        return ticketRepository.findByTravelDateAndIsDeletedFalse(date)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /* ==== Helpers ==== */

    private void validateRequest(TicketRequestDTO req) {
        if (req == null) throw new InvalidOperationException("Request cannot be null.");

        if (req.getPassengerName() == null || req.getPassengerName().isBlank())
            throw new InvalidOperationException("Passenger name is required.");
        if (req.getEmail() == null || req.getEmail().isBlank())
            throw new InvalidOperationException("Email is required.");
        if (req.getTrainId() == null)

throw new InvalidOperationException("Train ID is required.");
        if (req.getSource() == null || req.getSource().isBlank() ||
            req.getDestination() == null || req.getDestination().isBlank())
            throw new InvalidOperationException("Source and destination are required.");
        if (req.getSeatType() == null || req.getSeatType().isBlank())
            throw new InvalidOperationException("Seat type is required.");
        if (req.getBookingType() == null || req.getBookingType().isBlank())
            throw new InvalidOperationException("Booking type is required.");
        if (req.getTravelDate() == null)
            throw new InvalidOperationException("Travel date is required.");

        if (req.getTravelDate().isBefore(LocalDate.now())) {

throw new InvalidOperationException("Travel date must not be in the past.");
        }

        String seat = normalize(req.getSeatType());
        String booking = normalize(req.getBookingType());
        if (!VALID_SEATS.contains(seat)) {
            throw new InvalidOperationException("Invalid seat type. Allowed: AC, NON-AC, SLEEPER");
        }
        if (!VALID_BOOKINGS.contains(booking)) {
            throw new InvalidOperationException("Invalid booking type. Allowed: NORMAL, TATKAL");
        }

}

    private void validateRoute(TicketRequestDTO req, Train train) {
        if (!train.getSource().equalsIgnoreCase(req.getSource()) ||
            !train.getDestination().equalsIgnoreCase(req.getDestination())) {
            throw new InvalidOperationException("Source/Destination do not match train route.");
        }
    }

    private String normalize(String s) { return s == null ? null : s.trim().toUpperCase(); }

    private double pickFare(Train train, String seatType) {

 switch (seatType) {
            case "AC": return train.getAcFare();
            case "NON-AC": return train.getNonAcFare();
            case "SLEEPER": return train.getSleeperFare();
            default: throw new InvalidOperationException("Unknown seat type: " + seatType);
        }
    }

    private double computeFare(double baseFare, String bookingType) {
        double result = "TATKAL".equals(bookingType) ? baseFare * 1.25 : baseFare;
        return Math.round(result * 100.0) / 100.0;
    }

 private TicketResponseDTO toDTO(Ticket t) {
        return new TicketResponseDTO(
                t.getId(),
                t.getPassengerName(),
                t.getEmail(),
                t.getTrainId(),
                t.getSource(),
                t.getDestination(),
                t.getSeatType(),
                t.getBookingType(),
                t.getFare(),
                t.getTravelDate(),
                t.getStatus(),
                t.getIsDeleted(),
                t.getHighFareTicket(),
                t.getCreatedAt(),
                t.getUpdatedAt()
        );

}
}


