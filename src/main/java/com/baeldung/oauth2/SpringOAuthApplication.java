package com.baeldung.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;

@PropertySource("classpath:application-oauth2.properties")
@SpringBootApplication
public class SpringOAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOAuthApplication.class, args);
    }

    @Bean
    public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrationRepo, ServerOAuth2AuthorizedClientRepository authorizedClientRepo) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction filter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepo, authorizedClientRepo);
        return WebClient.builder()
                .filter(filter)
                .build();
    }

}
