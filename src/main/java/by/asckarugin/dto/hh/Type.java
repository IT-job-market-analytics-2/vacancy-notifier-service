package by.asckarugin.dto.hh;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Type {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
}
