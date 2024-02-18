package com.ethnoeats.logout;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class LogoutServiceImpl implements LogoutService {

    @Override
    public void logout(HttpServletRequest request) {
        // Invalidate the session
        request.getSession().invalidate();
    }
}
