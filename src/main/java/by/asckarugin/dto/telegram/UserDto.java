package by.asckarugin.dto.telegram;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String username;
    private Long telegramChatId;


}
