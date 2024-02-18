//package com.ethnoeats.login;
//
//import com.ethnoeats.auth.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginServiceImpl implements LoginService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private AuthService authService;
//
//    @Override
//    public String login(String username, String password) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password));
//
//        // Set the authentication in the security context
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Generate the token using the authenticated user's details
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        return authService.authenticateUser(userDetails.getUsername(), userDetails.getPassword());
//    }
//}
package com.ethnoeats.login;

import com.ethnoeats.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthService authService;

    @Override
    public String login(String username, String password) {
        // Assuming authService.authenticateUser() handles the authentication logic
        return authService.authenticateUser(username, password);
    }
}