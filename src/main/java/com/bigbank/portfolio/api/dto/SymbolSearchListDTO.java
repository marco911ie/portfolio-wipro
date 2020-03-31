package com.bigbank.portfolio.api.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SymbolSearchListDTO {
    private List<SymbolSearchDTO> bestMatches;
}
