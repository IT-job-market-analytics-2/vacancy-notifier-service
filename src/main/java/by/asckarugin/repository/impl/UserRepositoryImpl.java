package by.asckarugin.repository.impl;

import by.asckarugin.dto.telegram.UserDto;
import by.asckarugin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final RestTemplate template;

    @Value("${rest-api.host}")
    private String host;

    @Value("${rest-api.endpoint}")
    private String endpoint;

    public UserRepositoryImpl(RestTemplate template) {
        this.template = template;
    }

    @Override
    public List<UserDto> findUsersByQuery(String query) {
        String url = host.concat(endpoint).concat(query);

        return Arrays.stream(Objects.requireNonNull(template.getForObject(url, UserDto[].class))).toList();
    }
}
