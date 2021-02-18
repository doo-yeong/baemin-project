package com.dy.baeminclone.repository;

import com.dy.baeminclone.domain.Store;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StoreRepository {
    @PersistenceContext
    private EntityManager em;


    public List<Store> getStoreListByCategory(String category) {
        return em.createQuery("SELECT s FROM Store s WHERE s.category = :category", Store.class)
                .setParameter("category", category).getResultList();
    }

    public void save(Store store) {
        em.persist(store);
    }
}
