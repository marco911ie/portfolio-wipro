package com.bigbank.portfolio.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PortfolioDTO {


    private Long id;
    private String symbol;
    private String name;
    private String type;
    private Integer number;

}
