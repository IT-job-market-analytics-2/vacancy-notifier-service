package by.asckarugin.repository;

import by.asckarugin.dto.telegram.UserDto;

import java.util.List;

public interface UserRepository {
    List<UserDto> findUsersByQuery(String query);
}
