/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.trixi.trixi.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Honza
 */
@Entity
public class City {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cityId")
    @SequenceGenerator(name="cityId", sequenceName="cityId")
    @Column(name="id", nullable=false)
    private long id;
    
    @Column(name="code", nullable=false)
    private String code;
    
    @Column(name="name", nullable=false)
    private String name;
    
    public City(){
        
    }

    public City(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    
}
