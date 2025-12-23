package com.tcs.irctc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.irctc.entity.Ticket;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	List<Ticket> findByIsDeletedFalse();
	List<Ticket> findByPassengerNameContainingIgnoreCaseAndIsDeletedFalse(String passengerName);
	List<Ticket> findByEmailIgnoreCaseAndIsDeletedFalse(String email);
	List<Ticket> findByTrainIdAndIsDeletedFalse(Long trainId);
	List<Ticket> findByTravelDateAndIsDeletedFalse(LocalDate travelDate);
	List<Ticket> findByHighFareTicketTrueAndIsDeletedFalse();
}
