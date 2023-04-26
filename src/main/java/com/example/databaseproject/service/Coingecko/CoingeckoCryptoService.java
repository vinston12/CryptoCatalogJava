package com.example.databaseproject.service.Coingecko;

import com.example.databaseproject.Entity.Crypto;
import com.example.databaseproject.model.Coingecko.CryptoDTO;
import com.example.databaseproject.Repository.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CoingeckoCryptoService {

    List<CryptoDTO> dataRespons;

    private final CryptoRepository cryptoRepository;


    public List<CryptoDTO> CMCallCryptoList() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://jsonplaceholder.typicode.com/posts";

        CryptoDTO[] response = restTemplate.getForObject(url, CryptoDTO[].class);

        dataRespons = Arrays.asList(response);

        for (CryptoDTO cryptoDTO : dataRespons){
            Crypto crypto = new Crypto();
            crypto.setId(cryptoDTO.getId());
            crypto.setUserId(cryptoDTO.getUserId());
            crypto.setTitle(cryptoDTO.getTitle());
            crypto.setBody(cryptoDTO.getBody());
//            coingeckoEntity.setName(dataResponse.getName());
//            coingeckoEntity.setSymbol(dataResponse.getSymbol());
//            coingeckoEntity.setMarket_cap(dataResponse.getMarket_cap());
//            coingeckoEntity.setMarket_cap_rank(dataResponse.getMarket_cap_rank());
//            coingeckoEntity.setCurrent_price(dataResponse.getCurrent_price());
//            coingeckoEntity.setTotal_supply(dataResponse.getTotal_supply());
            cryptoRepository.save(crypto);
        }

        return dataRespons;
    }

}
