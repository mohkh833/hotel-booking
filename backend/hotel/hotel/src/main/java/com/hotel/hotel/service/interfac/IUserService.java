package com.hotel.hotel.service.interfac;

import com.hotel.hotel.dto.LoginRequest;
import com.hotel.hotel.dto.Response;
import com.hotel.hotel.entity.User;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

    Response confirmEmail(String confirmationToken);
}
