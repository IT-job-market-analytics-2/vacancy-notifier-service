package by.asckarugin.dto.hh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Employment {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
}
