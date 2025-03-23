package com.niolikon.taskboard.authenticator.auth;

import com.niolikon.taskboard.framework.security.JwtAuthenticationService;
import com.niolikon.taskboard.framework.security.dto.UserLoginRequest;
import com.niolikon.taskboard.framework.security.dto.UserLogoutRequest;
import com.niolikon.taskboard.framework.security.dto.UserTokenRefreshRequest;
import com.niolikon.taskboard.framework.security.dto.UserTokenView;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/Auth")
public class AuthController {

    private JwtAuthenticationService jwtAuthenticationService;

    public void setJwtAuthenticationService(final JwtAuthenticationService jwtAuthenticationService) {
        this.jwtAuthenticationService = jwtAuthenticationService;
    }


    @PostMapping("login")
    public ResponseEntity<UserTokenView> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        UserTokenView userTokenView = jwtAuthenticationService.login(userLoginRequest);
        return ok().body(userTokenView);
    }

    @PostMapping("refresh")
    public ResponseEntity<UserTokenView> refreshToken(@Valid @RequestBody UserTokenRefreshRequest userTokenRefreshRequest) {
        UserTokenView userTokenView = jwtAuthenticationService.refreshToken(userTokenRefreshRequest);
        return ok().body(userTokenView);
    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody UserLogoutRequest userLogoutRequest) {
        jwtAuthenticationService.logout(userLogoutRequest);
        return ok().build();
    }

}
