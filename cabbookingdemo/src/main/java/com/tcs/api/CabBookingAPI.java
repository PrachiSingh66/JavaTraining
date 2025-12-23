package com.tcs.api;

import com.tcs.dto.CabBookingDTO;
import com.tcs.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class CabBookingAPI {

    @Autowired
    private BookingService bookingService;

    /**
     * Book a cab
     * Endpoint: POST /bookings/
     * Body: CabBookingDTO
     * Response: "Booking created with ID: <id>"
     */
    @PostMapping("/")
    public ResponseEntity<String> bookCab(@RequestBody CabBookingDTO cabBookingDTO) {
        Integer bookingId = bookingService.bookCab(cabBookingDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Booking created with ID: " + bookingId);
    }

    /**
     * Get booking details by mobile number
     * Endpoint: GET /bookings/{mobileNo}
     * PathVar: mobileNo (Long)
     * Response: List<CabBookingDTO>
     */
    @GetMapping("/{mobileNo}")
    public ResponseEntity<List<CabBookingDTO>> getBookingDetails(@PathVariable Long mobileNo) {
        List<CabBookingDTO> bookings = bookingService.getBookingDetails(mobileNo);
        return ResponseEntity.ok(bookings);
    }

    /**
     * Cancel booking by bookingId
     * Endpoint: PUT /bookings/{bookingId}
     * PathVar: bookingId (Integer)
     * Response: "Booking cancelled"
     */
    @PutMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) {
               bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking cancelled");
    }
}

