package com.zhang.myproject;

import com.zhang.myproject.dto.UserDTO;
import com.zhang.myproject.repository.UserJpaRepository;
import com.zhang.myproject.rest.UserRegistrationRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.hamcrest.Matchers.is;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * create by zhangbo on 2019/11/6 0006
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = UserRegistrationRestController.class)
@ContextConfiguration(classes = HellospringbootApplication.class)
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserJpaRepository userJpaRepositoryMock;
    private MediaType contentType;
    private UserDTO user;
    private Optional<UserDTO> optUser;
    @Before
    public void setup() {
        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
        user = new UserDTO();
        user.setName("Ravi Kant Soni");
        user.setAddress("JP Nagar; Bangalore; India");
        user.setEmail("ravikantsoni.author@gmail.com");
        optUser = Optional.of(this.user);
    }

    @Test
    public void shouldReturnSuccessMessage() throws Exception {
        when(this.userJpaRepositoryMock.findById(1L)).thenReturn(optUser);
        this.mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.name", is("Ravi Kant Soni")))
                .andExpect(jsonPath("$.address", is("JP Nagar; Bangalore; India")))
                .andExpect(jsonPath("$.email", is("ravikantsoni.author@gmail.com")))
                .andDo(print());
    }

}
