package com.zhang.myproject;

import com.zhang.myproject.dto.UserDTO;
import com.zhang.myproject.repository.UserJpaRepository;
import com.zhang.myproject.rest.UserRegistrationRestController;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = HellospringbootApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class HellospringbootApplicationTests {


    @Spy
    private UserRegistrationRestController userRegistrationRestController;

    @Mock
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    public void setup() {
        System.out.println("before被调用");
        userRegistrationRestController = new UserRegistrationRestController();
        ReflectionTestUtils.setField(userRegistrationRestController, "userJpaRepository",userJpaRepository);

    }

    @Test
    public void testListAllUsers() {

        List<UserDTO> userList = new ArrayList<UserDTO>();
        userList.add(new UserDTO());
        when(this.userJpaRepository.findAll()).thenReturn(userList);
        ResponseEntity<List<UserDTO>> responseEntity = this.userRegistrationRestController.listAllUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());

    }
    @AfterEach
    public void teardown() {
        userRegistrationRestController = null;
    }
}
