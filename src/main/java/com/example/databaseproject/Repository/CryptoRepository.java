package com.example.databaseproject.Repository;

import com.example.databaseproject.Entity.Crypto;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CryptoRepository extends CrudRepository<Crypto, Integer>
        ,CustomCryptoRepository, JpaSpecificationExecutor<Crypto> {
}
