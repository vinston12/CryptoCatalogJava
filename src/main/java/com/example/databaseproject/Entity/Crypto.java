package com.example.databaseproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "CryptoList")
public class Crypto {


    @Id
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
//    private String name;
//    private String current_price;
//    private BigInteger market_cap;
//    @Id
//    private Integer market_cap_rank;
//    private BigDecimal total_supply;

}
