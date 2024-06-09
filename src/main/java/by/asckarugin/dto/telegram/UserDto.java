package by.asckarugin.dto.telegram;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    String username;
    Long telegramChatId;

    public UserDto(String username, Long telegramChatId){
        this.username = username;
        this.telegramChatId = telegramChatId;
    }

}
