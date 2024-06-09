package by.asckarugin.repository.impl;

import by.asckarugin.dto.telegram.UserDto;
import by.asckarugin.repository.UserRepository;

import java.util.List;

public class UserRepositoryMock implements UserRepository {

    @Override
    public List<UserDto> findUsersByQuery(String query) {
        return List.of(new UserDto("asckarugin", 1L));
    }
}
