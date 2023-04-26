package com.example.databaseproject.Repository;

import com.example.databaseproject.Entity.Crypto;

import java.util.List;

public interface CustomCryptoRepository {
    List<Crypto> findByIdAndDeparment(Integer userId,String title);
}
