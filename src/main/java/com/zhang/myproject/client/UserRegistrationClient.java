package com.zhang.myproject.client;

import com.zhang.myproject.dto.UserDTO;
import org.springframework.web.client.RestTemplate;

/**
 * create by zhangbo on 2019/11/6 0006
 */
public class UserRegistrationClient {

    private static RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080/api/user/";

    public UserDTO getUserById(final Long id) {

        return restTemplate.getForObject(BASE_URL + "/{id}", UserDTO.class, id);
    }

    public UserDTO[] getAllUsers() {

        return restTemplate.getForObject(BASE_URL, UserDTO[].class);
    }

    public UserDTO createUser(final UserDTO user) {
        return restTemplate.postForObject(BASE_URL, user, UserDTO.class);
    }

    public void updateUser(final Long userId, final UserDTO user) {
        restTemplate.put(BASE_URL + "/{id}", user, userId);
    }

    public void deleteUser(final Long userId) {
        restTemplate.delete(BASE_URL + "/{id}", userId);
    }


    public static void main(String[] args) {
        UserRegistrationClient userRegistrationClient = new UserRegistrationClient();
        UserDTO user = userRegistrationClient.getUserById(2L);
        System.out.println("USER-ID" + user.getId() + "USER-NAME" + user.getName());
//        user.setEmail("xiugai@qq.com");
//        userRegistrationClient.updateUser(2L, user);
//        System.out.println(user.getEmail());

        userRegistrationClient.deleteUser(2L);

//        UserDTO userDTO = new UserDTO();
//        userDTO.setName("张小龙2");
//        userDTO.setAddress("北京");
//        userDTO.setEmail("545454@qq.com");
//        UserDTO newUser = userRegistrationClient.createUser(userDTO);
//        System.out.println(newUser.getId());


    }

}
