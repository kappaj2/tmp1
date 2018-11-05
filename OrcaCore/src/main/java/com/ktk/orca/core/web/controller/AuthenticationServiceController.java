package com.ktk.orca.core.web.controller;

import com.ktk.orca.core.authapi.model.*;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.service.AuthenticationService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AuthenticationServiceController {

    private AuthenticationService authenticationService;

    public AuthenticationServiceController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Timed
    @Audited
    @PostMapping(value = "/auth/create")
    public ResponseEntity getUserCreate(@RequestBody final CreateRequest createRequest) {
        try {
            CreateResponse createResponse = authenticationService.userCreate(createRequest);
            return new ResponseEntity<>(createResponse, HttpStatus.OK);
        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @GetMapping("/loginandsignout")
    public ResponseEntity testLoginSignout() {

        try {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setFlowCode(LoginRequest.FlowCodeEnum.LOGIN);
            loginRequest.setUsername("kappaj");
            loginRequest.setPassword("NewPassword13@");

            LoginResponse loginResponse = authenticationService.userLogin(loginRequest);

            LogoutRequest logoutRequest = new LogoutRequest();
            logoutRequest.setAccessToken(loginResponse.getAccessToken());

            LogoutResponse logoutResponse = authenticationService.userLogout(logoutRequest);

            log.info("Response message: " + logoutResponse.getMessage());
            log.info("ResponseCode    : " + logoutResponse.getStatusCode());

            return new ResponseEntity<>(loginResponse, HttpStatus.OK);

        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @PostMapping(value = "/auth/login")
    public ResponseEntity getAuthLogin(@RequestBody final LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = authenticationService.userLogin(loginRequest);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @PostMapping(value = "/auth/logout")
    public ResponseEntity getUserLogout(@RequestBody final LogoutRequest logoutRequest) {
        try {
            LogoutResponse logoutResponse = authenticationService.userLogout(logoutRequest);
            return new ResponseEntity<>(logoutResponse, HttpStatus.OK);
        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @GetMapping(value = "/auth/refresh/{token}")
    public ResponseEntity getUserRefreshToken(@PathVariable final String token) {
        try {
            RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
            refreshTokenRequest.setRefreshToken(token);
            RefreshTokenResponse refreshTokenResponse = authenticationService.userRefreshToken(refreshTokenRequest);
            return new ResponseEntity<>(refreshTokenResponse, HttpStatus.OK);
        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @PostMapping(value = "/auth/resetpwd")
    public ResponseEntity getResetPassword(@RequestBody final ResetPasswordRequest resetPasswordRequest) {
        try {
            ResetPasswordResponse resetPasswordResponse = authenticationService.userResetPasswordRequest(resetPasswordRequest);
            return new ResponseEntity<>(resetPasswordResponse, HttpStatus.OK);
        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

    @Timed
    @Audited
    @PostMapping(value = "/auth/resetpwdconfirm")
    public ResponseEntity getResetPasswordConfirm(@RequestBody final ResetPasswordRequest resetPasswordRequest) {
        try {
            ResetPasswordResponse resetPasswordResponse = authenticationService.userResetPasswordConfirm(resetPasswordRequest);
            return new ResponseEntity<>(resetPasswordResponse, HttpStatus.OK);
        } catch (OrcaApiException mapie) {
            return new ResponseEntity<>(mapie.getApiError(), HttpStatus.valueOf(mapie.getApiError().getStatusCode()));
        }
    }

}
