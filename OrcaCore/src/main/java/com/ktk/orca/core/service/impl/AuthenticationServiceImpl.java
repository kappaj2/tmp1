package com.ktk.orca.core.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktk.orca.core.authapi.client.*;
import com.ktk.orca.core.authapi.invoker.ApiClient;
import com.ktk.orca.core.authapi.model.*;
import com.ktk.orca.core.config.ApplicationProperties;
import com.ktk.orca.core.exceptions.OrcaApiException;
import com.ktk.orca.core.exceptions.RestTemplateError;
import com.ktk.orca.core.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.time.Instant;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${application.clientApiDebugEnabled}")
    private boolean clientApiDebugEnabled;

    private UserLoginApi userLoginApi;
    private UserCreateApi userCreateApi;
    private UserLogoutApi userLogoutApi;
    private UserRefreshTokenApi userRefreshTokenApi;
    private UserResetPasswordRequestApi userResetPasswordRequestApi;
    private UserResetPasswordConfirmApi userResetPasswordConfirmApi;
    private ApplicationProperties props;
    private BaseServiceUtil baseServiceUtil;
    private MessageSource messageSource;

    public AuthenticationServiceImpl(UserLoginApi userLoginApi,
                                     UserCreateApi userCreateApi,
                                     UserLogoutApi userLogoutApi,
                                     UserRefreshTokenApi userRefreshTokenApi,
                                     UserResetPasswordRequestApi userResetPasswordRequestApi,
                                     UserResetPasswordConfirmApi userResetPasswordConfirmApi,
                                     ApplicationProperties props, BaseServiceUtil baseServiceUtil,
                                     MessageSource messageSource) {
        this.userLoginApi = userLoginApi;
        this.userCreateApi = userCreateApi;
        this.userLogoutApi = userLogoutApi;
        this.userRefreshTokenApi = userRefreshTokenApi;
        this.userResetPasswordRequestApi = userResetPasswordRequestApi;
        this.userResetPasswordConfirmApi = userResetPasswordConfirmApi;
        this.props = props;
        this.baseServiceUtil = baseServiceUtil;
        this.messageSource = messageSource;
    }


    /**
     * Create a new user using email and username for input.
     *
     * @param createRequest
     * @return
     * @throws OrcaApiException
     */
    public CreateResponse userCreate(CreateRequest createRequest) throws OrcaApiException {

        userCreateApi.getApiClient().setBasePath(props.getOrcaApi().getAuthUri());

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        CreateResponse createResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();
        try {

            objectNode.put("username", createRequest.getUsername());
            objectNode.put("email", createRequest.getEmail());

            ApiClient apiClient = userCreateApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            createResponse = userCreateApi.userCreate(createRequest);

            responseCode = apiClient.getStatusCode().value();

            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return createResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.account.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {

            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {
            baseServiceUtil.writeTransactionRecord("AuthenticationService",
                    "userCreate",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    createResponse);
        }

    }

    /**
     * Login the user. A LoginState result indicator will show whether a new Password is required or if login is successful.
     *
     * @param loginRequest
     * @return
     * @throws OrcaApiException
     */
    public LoginResponse userLogin(LoginRequest loginRequest) throws OrcaApiException {

        userLoginApi.getApiClient().setBasePath(props.getOrcaApi().getAuthUri());

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        LoginResponse loginResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();

        try {

            objectNode.put("username", loginRequest.getUsername());
            objectNode.put("flow_code", loginRequest.getFlowCode().toString());

            ApiClient apiClient = userLoginApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            loginResponse = userLoginApi.userLogin(loginRequest);

            responseCode = apiClient.getStatusCode().value();

            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return loginResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.account.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {

            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {
            baseServiceUtil.writeTransactionRecord("AuthenticationService",
                    "userLogin",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    loginResponse);
        }
    }

    /**
     * Logout the user. This will invalidate the user session.
     *
     * @param logoutRequest
     * @return
     * @throws OrcaApiException
     */
    public LogoutResponse userLogout(LogoutRequest logoutRequest) throws OrcaApiException {
        userLogoutApi.getApiClient().setBasePath(props.getOrcaApi().getAuthUri());

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        LogoutResponse logoutResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();
        try {

            objectNode.put("accessToken", logoutRequest.getAccessToken());

            ApiClient apiClient = userLogoutApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            logoutResponse = userLogoutApi.userLogout(logoutRequest);

            responseCode = apiClient.getStatusCode().value();

            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return logoutResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.account.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {

            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {
            baseServiceUtil.writeTransactionRecord("AuthenticationService",
                    "userCreate",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    logoutResponse);
        }
    }

    /**
     * Refresh user token.
     *
     * @param refreshTokenRequest
     * @return
     * @throws OrcaApiException
     */
    public RefreshTokenResponse userRefreshToken(RefreshTokenRequest refreshTokenRequest) throws OrcaApiException {
        userRefreshTokenApi.getApiClient().setBasePath(props.getOrcaApi().getAuthUri());

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        RefreshTokenResponse refreshTokenResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();
        try {

            objectNode.put("refreshToken", refreshTokenRequest.getRefreshToken());

            ApiClient apiClient = userRefreshTokenApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            refreshTokenResponse = userRefreshTokenApi.userRefreshToken(refreshTokenRequest);

            responseCode = apiClient.getStatusCode().value();

            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return refreshTokenResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.account.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {

            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {
            baseServiceUtil.writeTransactionRecord("AuthenticationService",
                    "userRefreshToken",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    refreshTokenResponse);
        }
    }

    /**
     * Reset the user password.
     *
     * @param resetPasswordRequest
     * @return
     * @throws OrcaApiException
     */
    public ResetPasswordResponse userResetPasswordRequest(ResetPasswordRequest resetPasswordRequest) throws OrcaApiException {
        userResetPasswordRequestApi.getApiClient().setBasePath(props.getOrcaApi().getAuthUri());

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        ResetPasswordResponse resetPasswordResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();
        try {

            objectNode.put("username", resetPasswordRequest.getUsername());
            objectNode.put("code", resetPasswordRequest.getCode());
            objectNode.put("password", resetPasswordRequest.getNewPassword());

            ApiClient apiClient = userResetPasswordRequestApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            resetPasswordResponse = userResetPasswordRequestApi.userResetPasswordRequest(resetPasswordRequest);

            responseCode = apiClient.getStatusCode().value();

            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return resetPasswordResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.account.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {

            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {
            baseServiceUtil.writeTransactionRecord("AuthenticationService",
                    "userPasswordReset",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    resetPasswordResponse);
        }
    }

    /**
     * Confirm the user password reset.
     *
     * @param resetPasswordRequest
     * @return
     * @throws OrcaApiException
     */
    public ResetPasswordResponse userResetPasswordConfirm(ResetPasswordRequest resetPasswordRequest) throws OrcaApiException {
        userResetPasswordConfirmApi.getApiClient().setBasePath(props.getOrcaApi().getAuthUri());

        int responseCode = -1;
        Instant startTime = Instant.now();
        String responseMessage = null;
        ResetPasswordResponse resetPasswordResponse = null;
        ObjectNode objectNode = baseServiceUtil.createNewObjectNode();
        try {

            objectNode.put("username", resetPasswordRequest.getUsername());
            objectNode.put("code", resetPasswordRequest.getCode());
            objectNode.put("newPAssword", resetPasswordRequest.getNewPassword());

            ApiClient apiClient = userResetPasswordConfirmApi.getApiClient();
            apiClient.setDebugging(clientApiDebugEnabled);

            resetPasswordResponse = userResetPasswordConfirmApi.userResetPasswordConfirm(resetPasswordRequest);

            responseCode = apiClient.getStatusCode().value();

            responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

            return resetPasswordResponse;

        } catch (RestClientException rce) {

            responseCode = 500;
            responseMessage = messageSource.getMessage("ktk.api.rest.account.exception", null, LocaleContextHolder.getLocale());
            throw new OrcaApiException(responseMessage, baseServiceUtil.buildBaseException(rce, responseCode), rce);

        } catch (RestTemplateError rte) {

            responseCode = rte.getApiError().getStatusCode();
            responseMessage = rte.getApiError().getMessage();
            throw new OrcaApiException(responseMessage, rte.getApiError());

        } finally {
            baseServiceUtil.writeTransactionRecord("AuthenticationService",
                    "userCreate",
                    startTime,
                    objectNode,
                    responseCode,
                    responseMessage,
                    resetPasswordResponse);
        }
    }
}
