package com.tcs.irctc.service;


import com.tcs.irctc.dto.TicketRequestDTO;
import com.tcs.irctc.dto.TicketResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    TicketResponseDTO book(TicketRequestDTO request);
    List<TicketResponseDTO> getAll();
    TicketResponseDTO getById(Long id);
    TicketResponseDTO update(Long id, TicketRequestDTO request);
    void cancel(Long id);
    TicketResponseDTO restore(Long id);
    List<TicketResponseDTO> search(String passengerName, String email, Long trainId, LocalDate travelDate);
    List<TicketResponseDTO> highFare();
    List<TicketResponseDTO> byTravelDate(LocalDate date);
}
