package by.asckarugin.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelegramDto {

    @JsonProperty("chat_id")
    private Long chat_id;

    private String message;
}
