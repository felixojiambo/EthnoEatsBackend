package com.ethnoeats.logout;

import jakarta.servlet.http.HttpServletRequest;

public interface LogoutService {

    void logout(HttpServletRequest request);
}
