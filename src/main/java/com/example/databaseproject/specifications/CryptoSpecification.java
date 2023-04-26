package com.example.databaseproject.specifications;

import com.example.databaseproject.Entity.Crypto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CryptoSpecification {

    public static Specification<Crypto> hasTtile(String title){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("title"), "%" +title + "%");
        });
    }
    public static Specification<Crypto> hasUsedId(Integer userId){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("userId"),userId);
        });
    }


}

