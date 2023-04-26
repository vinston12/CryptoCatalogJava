package com.example.databaseproject.Repository;

import com.example.databaseproject.Entity.Crypto;
import com.github.tennaito.rsql.jpa.JpaCriteriaCountQueryVisitor;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.Collections;
import java.util.List;

public class CustomCryptoRepositoryImpl implements CustomCryptoRepository {

    private final EntityManager en;

    public CustomCryptoRepositoryImpl(EntityManager en) {
        this.en = en;
    }

    @Override
    public List<Crypto> findByIdAndDeparment(Integer userId, String title) {
        CriteriaBuilder cb = en.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();


        Root<Crypto> cryptoRoot = cq.from(Crypto.class);

        Predicate userIdIdPredicate = cb.equal(cryptoRoot.get("userId"), userId);
        Predicate idPredicate = cb.like(cryptoRoot.get("title"), "%"+title+"%");

        cq.where(idPredicate,userIdIdPredicate);

        TypedQuery<Crypto> query = en.createQuery(cq);

        return query.getResultList();


    }

    public List<Crypto> searchByQuery(String queryString) {
        RSQLVisitor<CriteriaQuery<Crypto>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
        CriteriaQuery<Crypto> query;
        query = getCriteriaQuery(queryString, visitor);
        List<Crypto> resultList = en.createQuery(query).getResultList();
        if (resultList == null || resultList.isEmpty()){
            return Collections.emptyList();
        }
        return resultList;
    }
    public Long countByQuery(String queryString) {
        RSQLVisitor<CriteriaQuery<Long>, EntityManager> visitor = new JpaCriteriaCountQueryVisitor<Crypto>();
        CriteriaQuery<Long> query;
        query = getCriteriaQuery(queryString, visitor);

        return en.createQuery(query).getSingleResult();
    }

    private <T> CriteriaQuery<T> getCriteriaQuery(String queryString, RSQLVisitor<CriteriaQuery<T>, EntityManager> visitor) {
        Node rootNode;
        CriteriaQuery<T> query;
        try {
            rootNode = new RSQLParser().parse(queryString);
            query = rootNode.accept(visitor, en);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return query;
    }



    @Override
    public Object visit(AndNode node, Object param) {
        return null;
    }

    @Override
    public Object visit(OrNode node, Object param) {
        return null;
    }

    @Override
    public Object visit(ComparisonNode node, Object param) {
        return null;
    }
}
