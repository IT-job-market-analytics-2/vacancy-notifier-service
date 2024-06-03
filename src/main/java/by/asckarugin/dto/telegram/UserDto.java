package by.asckarugin.dto.telegram;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    String username;
    Long telegramChatId;
}
