package com.example.components;

import com.example.handlers.UserHandler;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserRouter.class, UserHandler.class})
@WebFluxTest
public class TestEmployeeHandler {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private UserRepository userRepository;

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void testGetUserById() {
        User user = User.builder().id(1).name("ABC").email("abc@xyz.com").build();
        Mono<User> UserMono = Mono.just(user);
        when(userRepository.findById(1)).thenReturn(UserMono);
        webTestClient.get()
                .uri("/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getId()).isEqualTo(1);
                            Assertions.assertThat(userResponse.getName()).isEqualTo("ABC");
                            Assertions.assertThat(userResponse.getEmail()).isEqualTo("abc@xyz.com");
                        }
                );

    }

    @Test
    public void testGetUsers() {

        User user1 = User.builder().id(1).name("ABC").email("abc@xyz.com").build();
        User user2 = User.builder().id(2).name("XYZ").email("xyz@abc.com").build();

        when(userRepository.findAll()).thenReturn(Flux.just(user1, user2));

        webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getId()).isEqualTo(1);
                            Assertions.assertThat(userResponse.get(0).getName()).isEqualTo("ABC");
                            Assertions.assertThat(userResponse.get(0).getEmail()).isEqualTo("abc@xyz.com");
                            Assertions.assertThat(userResponse.get(1).getId()).isEqualTo(2);
                            Assertions.assertThat(userResponse.get(1).getName()).isEqualTo("XYZ");
                            Assertions.assertThat(userResponse.get(1).getEmail()).isEqualTo("xyz@abc.com");

                        }
                );

    }

    @Test
    public void testCreateUser() {

        User user = User.builder().id(1).name("ABC").email("abc@xyz.com").build();
        Mono<User> UserMono = Mono.just(user);
        when(userRepository.save(any())).thenReturn(UserMono);

        webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getId()).isEqualTo(1);
                            Assertions.assertThat(userResponse.getName()).isEqualTo("ABC");
                            Assertions.assertThat(userResponse.getEmail()).isEqualTo("abc@xyz.com");
                        }
                );

    }
}
