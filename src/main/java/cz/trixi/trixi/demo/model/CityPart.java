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
public class CityPart {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cityPartId")
    @SequenceGenerator(name="cityPartId", sequenceName="cityPartId")
    @Column(name="id", nullable=false)
    private long id;
    
    @Column(name="code", nullable=false)
    private String code;
    
    @Column(name="name", nullable=false)
    private String name;
    
    @Column(name="cityCode", nullable=false)
    private String cityCode;
    
    public CityPart(){
        
    }

    public CityPart(String code, String name, String belongsTo_CityCode) {
        this.code = code;
        this.name = name;
        this.cityCode = belongsTo_CityCode;
    }
      
}
