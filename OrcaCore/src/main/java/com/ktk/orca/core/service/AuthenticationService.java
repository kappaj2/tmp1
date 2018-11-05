package com.ktk.orca.core.service;

import com.ktk.orca.core.authapi.model.*;
import com.ktk.orca.core.exceptions.OrcaApiException;

public interface AuthenticationService {

    CreateResponse userCreate(CreateRequest createRequest) throws OrcaApiException;

    LoginResponse userLogin(LoginRequest loginRequest) throws OrcaApiException;

    LogoutResponse userLogout(LogoutRequest logoutRequest) throws OrcaApiException;

    RefreshTokenResponse userRefreshToken(RefreshTokenRequest refreshTokenRequest) throws OrcaApiException;

    ResetPasswordResponse userResetPasswordRequest(ResetPasswordRequest resetPasswordRequest) throws OrcaApiException;

    ResetPasswordResponse userResetPasswordConfirm(ResetPasswordRequest resetPasswordRequest) throws OrcaApiException;
}
