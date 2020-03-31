package com.bigbank.portfolio.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SymbolSearchDTO {

    @JsonProperty(value = "1. symbol")
    private String symbol;
    @JsonProperty(value = "2. name")
    private String name;
    @JsonProperty(value = "3. type")
    private String type;
    @JsonProperty(value = "5. marketOpen")
    private String marketOpen;
    @JsonProperty(value = "6. marketClose")
    private String marketClose;
}
