package com.example.databaseproject.controller.Coingecko;


import com.example.databaseproject.Entity.Crypto;
import com.example.databaseproject.Repository.CustomCryptoRepository;
import com.example.databaseproject.Repository.CustomCryptoRepositoryImpl;
import com.example.databaseproject.model.Coingecko.CryptoDTO;
import com.example.databaseproject.Repository.CryptoRepository;
import com.example.databaseproject.service.Coingecko.CoingeckoCryptoService;
import com.example.databaseproject.specifications.CryptoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.data.jpa.domain.Specification.where;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class CoigneckoCapController {

    private final CoingeckoCryptoService coingeckoCryptoService;
    private final CryptoRepository cryptoRepository;
    private final CustomCryptoRepositoryImpl customCryptoRepository;
    @GetMapping("map")
    @ResponseBody
    public List<CryptoDTO> CMCallCryptoList() {
        List<CryptoDTO> cryptoList = coingeckoCryptoService.CMCallCryptoList();
        return cryptoList;
    }
    @GetMapping("list")
    public ResponseEntity get(){
        Iterable<Crypto> all = cryptoRepository.findAll();

        return ResponseEntity.ok(all);
    }

    @GetMapping("crypto")
    @ResponseBody
    public List<Crypto> findByIdAndTitle(@RequestParam Integer userId,
                                         @RequestParam String title){


        return  cryptoRepository.findAll(where(CryptoSpecification.hasUsedId(userId)
                .and(CryptoSpecification.hasTtile(title))));
    }
    @GetMapping(path = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Crypto>> query(@RequestParam(value = "q") String query) {
        List<Crypto> result = null;
        try {
            result= customCryptoRepository.searchByQuery(query);
        }catch (IllegalArgumentException iae){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(result);
        }
        return  ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}
