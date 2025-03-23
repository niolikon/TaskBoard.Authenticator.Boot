package com.niolikon.taskboard.authenticator.auth.scenarios;

import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;
import java.util.List;

public class TodoRealmWithSingleUserTestScenario {
    public static final String REALM_NAME = "TodoRealm";

    public static final String CLIENT_ID = "my-client";
    public static final String CLIENT_PROTOCOL = "openid-connect";

    public static final String USER_USERNAME = "testuser";
    public static final String USER_PASSWORD = "testuser2025password54f3!";
    public static final String USER_FIRSTNAME = "Mario";
    public static final String USER_LASTNAME = "Rossi";
    public static final String USER_EMAIL = "testuser@example.com";
    public static final List<String> USER_ROLES = List.of("USER");

    public static RealmRepresentation getRealm() {
        RealmRepresentation realm = new RealmRepresentation();
        realm.setRealm(REALM_NAME);
        realm.setEnabled(Boolean.TRUE);

        ClientRepresentation client = new ClientRepresentation();
        client.setClientId(CLIENT_ID);
        client.setPublicClient(Boolean.TRUE);
        client.setDirectAccessGrantsEnabled(Boolean.TRUE);
        client.setWebOrigins(List.of("*"));
        client.setProtocol(CLIENT_PROTOCOL);

        realm.setClients(Collections.singletonList(client));

        UserRepresentation user = new UserRepresentation();
        user.setUsername(USER_USERNAME);
        user.setFirstName(USER_FIRSTNAME);
        user.setLastName(USER_LASTNAME);
        user.setEnabled(Boolean.TRUE);
        user.setEmailVerified(Boolean.TRUE);
        user.setEmail(USER_EMAIL);

        CredentialRepresentation password = new CredentialRepresentation();
        password.setType(CredentialRepresentation.PASSWORD);
        password.setValue(USER_PASSWORD);
        password.setTemporary(Boolean.FALSE);
        user.setCredentials(Collections.singletonList(password));

        user.setRealmRoles(USER_ROLES);
        user.setRequiredActions(Collections.emptyList());

        realm.setUsers(Collections.singletonList(user));

        return realm;
    }
}
