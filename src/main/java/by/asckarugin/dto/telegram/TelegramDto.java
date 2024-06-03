package by.asckarugin.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TelegramDto {

    @JsonProperty("chat_id")
    private Long chat_id;

    private String message;
}
