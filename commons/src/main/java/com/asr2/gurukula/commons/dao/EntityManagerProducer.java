package com.asr2.gurukula.commons.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by abhijith.nagarajan on 7/17/15.
 */
@ApplicationScoped
class EntityManagerProducer {

    @PersistenceUnit(unitName = "GovuPU")
    EntityManagerFactory emf;

    @Produces
    @Default
    @RequestScoped
    public EntityManager create() {
        return this.emf.createEntityManager();
    }

    public void dispose(@Disposes @Default EntityManager em) {
        if(em.isOpen())
            em.close();
    }
}
