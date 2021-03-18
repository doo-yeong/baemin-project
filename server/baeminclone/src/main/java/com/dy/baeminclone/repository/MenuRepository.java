package com.dy.baeminclone.repository;

import com.dy.baeminclone.domain.Menu;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MenuRepository {
    @PersistenceContext
    private EntityManager em;

    public void saveMenu(Menu menu){
        em.persist(menu);
    }
}
