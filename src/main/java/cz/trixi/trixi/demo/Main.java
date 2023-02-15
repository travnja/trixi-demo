package cz.trixi.trixi.demo;

import cz.trixi.trixi.demo.model.City;
import cz.trixi.trixi.demo.model.CityPart;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Honza
 */
public class Main {

    static byte buffer[] = new byte[1024];
    static int bytesRead;

    public static void main(String[] args) throws Exception {
        String url = "https://www.smartform.cz/download/kopidlno.xml.zip";
        String basePath = System.getProperty("user.dir") + "/data";
        String zipFilePath = basePath + "/downloadedZip.zip";
        String unzippedFilePath = basePath + "/unzipped.xml";

        //file download
        Main.downloadZip(url, zipFilePath);

        //unziping and parsing XML data
        Main.unzipData(zipFilePath, unzippedFilePath);
        
        //parse wanted values to Objects
        XMLParser parser = new XMLParser(unzippedFilePath);
        List<City> cities = parser.getCities();
        List<CityPart> cityParts = parser.getCityParts();

        //post to database
        postToDatabase(cities, cityParts);

    }

    public static void downloadZip(String url, String zipFilePath) throws Exception {
        BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
        FileOutputStream file = new FileOutputStream(zipFilePath);

        while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
            file.write(buffer, 0, bytesRead);
        }
    }

    public static void unzipData(String zipFilePath, String unzippedFilePath) throws Exception{
        FileOutputStream unzippedFile = new FileOutputStream(unzippedFilePath);
        ZipInputStream zipFile = new ZipInputStream(new FileInputStream(zipFilePath));
        zipFile.getNextEntry();

        while ((bytesRead = zipFile.read(buffer)) > 0) {
            unzippedFile.write(buffer, 0, bytesRead);
        }
    }
    
    public static void postToDatabase(List<City> cities, List<CityPart> cityParts){
        DatabaseHandler handler = new DatabaseHandler();
        cities.forEach(c -> {
            handler.saveCityToDatabase(c);
        });

        cityParts.forEach(cp -> {
            handler.saveCityPartToDatabase(cp);
        });
    }
}
