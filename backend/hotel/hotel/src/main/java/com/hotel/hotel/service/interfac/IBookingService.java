package com.hotel.hotel.service.interfac;

import com.hotel.hotel.dto.Response;
import com.hotel.hotel.entity.Booking;

public interface IBookingService {
    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);
}
