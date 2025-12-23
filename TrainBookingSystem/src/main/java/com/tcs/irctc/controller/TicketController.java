package com.tcs.irctc.controller;

import com.tcs.irctc.dto.TicketRequestDTO;
import com.tcs.irctc.dto.TicketResponseDTO;
import com.tcs.irctc.entity.Train;
import com.tcs.irctc.repository.TrainRepository;
import com.tcs.irctc.service.TicketService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TrainRepository trainRepository;

    public TicketController(TicketService ticketService, TrainRepository trainRepository) {
        this.ticketService = ticketService;
        this.trainRepository = trainRepository;
    }

    /** POST /tickets : Book a new ticket */
    @PostMapping
    public ResponseEntity<TicketResponseDTO> book(@RequestBody TicketRequestDTO request) {
        return ResponseEntity.ok(ticketService.book(request));
    }

    /** GET /tickets : Get all non-deleted tickets */
    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAll() {
        return ResponseEntity.ok(ticketService.getAll());
    }

    /** GET /tickets/{id} : Get ticket by ID */
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getById(id));
    }

    /** PUT /tickets/{id} : Update ticket details */
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> update(@PathVariable Long id,
                                                    @RequestBody TicketRequestDTO request) {
        return ResponseEntity.ok(ticketService.update(id, request));
    }

    /** DELETE /tickets/{id} : Cancel ticket (soft delete) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        ticketService.cancel(id);
        return ResponseEntity.noContent().build();
    }

    /** PUT /tickets/{id}/restore : Restore a cancelled ticket */
    @PutMapping("/{id}/restore")
    public ResponseEntity<TicketResponseDTO> restore(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.restore(id));
    }

    /** GET /tickets/search : Search tickets by fields */
    @GetMapping("/search")
    public ResponseEntity<List<TicketResponseDTO>> search(
            @RequestParam(required = false) String passengerName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long trainId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate) {
        return ResponseEntity.ok(ticketService.search(passengerName, email, trainId, travelDate));
    }

    /** GET /tickets/high-fare : Get high-fare tickets (> 2000) */
    @GetMapping("/high-fare")
    public ResponseEntity<List<TicketResponseDTO>> highFare() {
        return ResponseEntity.ok(ticketService.highFare());
    }

    /** GET /tickets/date?date=YYYY-MM-DD : Get tickets by travel date */
    @GetMapping("/date")
    public ResponseEntity<List<TicketResponseDTO>> byDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(ticketService.byTravelDate(date));
    }

    /** GET /trains : Get all train routes */
    @GetMapping("/trains")
    public ResponseEntity<List<Train>> trains() {
        return ResponseEntity.ok(trainRepository.findAll());
    }
}

