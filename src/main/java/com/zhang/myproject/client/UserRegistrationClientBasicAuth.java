package com.zhang.myproject.client;

import com.zhang.myproject.dto.UserDTO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * create by zhangbo on 2019/11/6 0006
 *
 * restTemplate 的exchange用法``以及带验证的访问
 */
public class UserRegistrationClientBasicAuth {

    private static final String securityUserName = "admin";
    private static final String securityUserPassword = "password";
    private static final String USER_REGISTRATION_BASE_URL = "http://localhost:8080/api/user/";
    private static RestTemplate restTemplate = new RestTemplate();

    public void deleteUserById(Long userId) {
        String userCredential = securityUserName + ":" + securityUserPassword;
        byte[] base64UserCredentialData = Base64.encodeBase64(userCredential.getBytes());
        HttpHeaders authenticationHeaders = new HttpHeaders();
        authenticationHeaders.set("Authorization", "Basic " + new String(base64UserCredentialData));
        HttpEntity<Void> httpEntity = new HttpEntity<Void>(authenticationHeaders);
        restTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}", HttpMethod.DELETE, httpEntity, Void.class, userId);

    }

    public ResponseEntity<UserDTO> getUserByIdUsingExchangeAPI(final Long userId) {
        HttpEntity<UserDTO> httpEntity = new HttpEntity<UserDTO>(new UserDTO());
        return restTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}", HttpMethod.GET, httpEntity, UserDTO.class, userId);
    }

    public static void main(String[] args) {
        UserRegistrationClientBasicAuth userRegistrationClientBasicAuth = new UserRegistrationClientBasicAuth();
        ResponseEntity<UserDTO> user = userRegistrationClientBasicAuth.getUserByIdUsingExchangeAPI(3L);
        System.out.println(user.getBody().getName());

        userRegistrationClientBasicAuth.deleteUserById(3L);
    }
}
