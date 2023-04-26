package com.example.databaseproject.model.Coingecko;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoDTO {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
//    private String id;
//    private String symbol;
//    private String name;
//    private String current_price;
//    private BigInteger market_cap;
//    private Integer market_cap_rank;
//    private BigDecimal total_supply;

}
