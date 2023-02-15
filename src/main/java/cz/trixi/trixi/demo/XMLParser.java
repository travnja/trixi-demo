/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.trixi.trixi.demo;

import cz.trixi.trixi.demo.model.City;
import cz.trixi.trixi.demo.model.CityPart;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Honza
 */
public class XMLParser {

    private final String filename;
    private final DocumentBuilderFactory docFactory;

    private List<City> cities;
    private List<CityPart> cityParts;

    public XMLParser(String filename) {
        this.docFactory = DocumentBuilderFactory.newInstance();
        this.filename = filename;
    }

    public List<City> getCities() throws Exception {
        if (cities == null) {
            this.parseCities();
        }
        return cities;
    }

    public List<CityPart> getCityParts() throws Exception {
        if (cityParts == null) {
            this.parseCities();
        }
        return cityParts;
    }

    public void parseCities() throws Exception {
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File(this.filename));
        doc.getDocumentElement().normalize();

        NodeList citiesDataList = doc.getElementsByTagName("vf:Obec");
        NodeList cityPartsDataList = doc.getElementsByTagName("vf:CastObce");
        
        cities = new ArrayList();
        cityParts = new ArrayList();
        
        String name, code, belongsToCity;

        for(int i = 0; i < citiesDataList.getLength(); i++){
            Node n = citiesDataList.item(i);
            if(n.getNodeType() == Node.ELEMENT_NODE){
                Element e = ((Element) n);
                
                name = e.getElementsByTagName("obi:Nazev").item(0).getTextContent();
                code = e.getElementsByTagName("obi:Kod").item(0).getTextContent();
                cities.add(new City(code, name));
            }
        }
        
        for(int i = 0; i < cityPartsDataList.getLength(); i++){
            Node n = cityPartsDataList.item(i);
            if(n.getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element) n;
                name = e.getElementsByTagName("coi:Nazev").item(0).getTextContent();
                code = e.getElementsByTagName("coi:Kod").item(0).getTextContent();
                
                Element city = (Element) e.getElementsByTagName("coi:Obec").item(0);
                belongsToCity = city.getElementsByTagName("obi:Kod").item(0).getTextContent();
                
                cityParts.add(new CityPart(code, name, code));
                
                
                
            }
        }
    }

}

