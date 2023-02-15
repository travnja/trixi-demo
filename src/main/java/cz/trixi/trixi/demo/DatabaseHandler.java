/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.trixi.trixi.demo;

import cz.trixi.trixi.demo.model.City;
import cz.trixi.trixi.demo.model.CityPart;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Honza
 */
public class DatabaseHandler {
    private String databaseUrl;
    private EntityManager em;
    private EntityManagerFactory emFactory;

    public DatabaseHandler() {
        this.databaseUrl = "";
        emFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        em = emFactory.createEntityManager();
    }

    public void saveCityToDatabase(City city) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(city);
        et.commit();
    }

    public void saveCityPartToDatabase(CityPart cityPart) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(cityPart);
        et.commit();
    }
}
