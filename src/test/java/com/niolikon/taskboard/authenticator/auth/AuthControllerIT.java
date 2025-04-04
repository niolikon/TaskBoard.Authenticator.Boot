package com.niolikon.taskboard.authenticator.auth;

import com.niolikon.taskboard.authenticator.auth.scenarios.TodoRealmWithSingleUserTestScenario;
import com.niolikon.taskboard.framework.security.dto.UserLoginRequest;
import com.niolikon.taskboard.framework.security.dto.UserLogoutRequest;
import com.niolikon.taskboard.framework.security.dto.UserTokenRefreshRequest;
import com.niolikon.taskboard.framework.security.dto.UserTokenView;
import com.niolikon.taskboard.framework.test.annotations.WithIsolatedKeycloakTestScenario;
import com.niolikon.taskboard.framework.test.containers.KeycloakTestContainersConfig;
import com.niolikon.taskboard.framework.test.extensions.IsolatedKeycloakTestScenarioExtension;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
@Import(KeycloakTestContainersConfig.class)
@ExtendWith(IsolatedKeycloakTestScenarioExtension.class)
class AuthControllerIT {

    @Autowired
    AuthController authController;

    @Test
    @WithIsolatedKeycloakTestScenario(dataClass = TodoRealmWithSingleUserTestScenario.class)
    void givenValidLoginRequest_whenLogin_thenReturnsUserTokenView() {
        UserLoginRequest loginRequest = new UserLoginRequest(TodoRealmWithSingleUserTestScenario.USER_USERNAME, TodoRealmWithSingleUserTestScenario.USER_PASSWORD);

        ResponseEntity<UserTokenView> loginResponse = authController.login(loginRequest);

        assertThat(loginResponse.getStatusCode()).isEqualTo(OK);
        assertThat(loginResponse.getBody()).isNotNull();
        assertThat(loginResponse.getBody())
                .extracting(
                        UserTokenView::getAccessToken,
                        UserTokenView::getRefreshToken)
                .allSatisfy( field -> {
                    assertThat(field)
                            .isInstanceOf(String.class)
                            .asInstanceOf(InstanceOfAssertFactories.STRING)
                            .isNotBlank();
                });
    }

    @Test
    @WithIsolatedKeycloakTestScenario(dataClass = TodoRealmWithSingleUserTestScenario.class)
    void givenValidRefreshRequest_whenRefreshToken_thenReturnsUserTokenView() {
        UserLoginRequest loginRequest = new UserLoginRequest(TodoRealmWithSingleUserTestScenario.USER_USERNAME, TodoRealmWithSingleUserTestScenario.USER_PASSWORD);

        ResponseEntity<UserTokenView> loginResponse = authController.login(loginRequest);
        UserTokenRefreshRequest refreshRequest = new UserTokenRefreshRequest(Objects.requireNonNull(loginResponse.getBody()).getRefreshToken());
        ResponseEntity<UserTokenView> refreshResponse = authController.refreshToken(refreshRequest);

        assertThat(refreshResponse.getStatusCode()).isEqualTo(OK);
        assertThat(refreshResponse.getBody()).isNotNull();
        assertThat(refreshResponse.getBody())
                .extracting(
                        UserTokenView::getAccessToken,
                        UserTokenView::getRefreshToken)
                .allSatisfy( field -> {
                    assertThat(field)
                            .isInstanceOf(String.class)
                            .asInstanceOf(InstanceOfAssertFactories.STRING)
                            .isNotBlank();
                });
    }

    @Test
    @WithIsolatedKeycloakTestScenario(dataClass = TodoRealmWithSingleUserTestScenario.class)
    void givenValidLogoutRequest_whenLogout_thenReturnsOk() {
        UserLoginRequest loginRequest = new UserLoginRequest(TodoRealmWithSingleUserTestScenario.USER_USERNAME, TodoRealmWithSingleUserTestScenario.USER_PASSWORD);

        ResponseEntity<UserTokenView> loginResponse = authController.login(loginRequest);
        UserLogoutRequest logoutRequest = new UserLogoutRequest(Objects.requireNonNull(loginResponse.getBody()).getRefreshToken());
        ResponseEntity<Void> logoutResponse = authController.logout(logoutRequest);

        assertThat(logoutResponse.getStatusCode()).isEqualTo(OK);
        assertThat(logoutResponse.getBody()).isNull();
    }
}
