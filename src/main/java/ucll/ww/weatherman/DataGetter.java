/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucll.ww.weatherman;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


/**
 *
 * @author Wouter
 */
public class DataGetter {
    //user: wouter35 (not used)
    //key: 3e758fde9069c39c
    //example: http://api.wunderground.com/api/3e758fde9069c39c/conditions/q/CA/San_Francisco.json
    static String zaventem = "EBBR";
    
    public static void main(String[] args){
        DataGetter dataGetter = new DataGetter();
        String exampleUrl = "http://api.wunderground.com/api/3e758fde9069c39c/conditions/q/CA/San_Francisco.json";
        try{
        dataGetter.getInfo(exampleUrl);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
   public WeatherInfo getInfo(String url) throws MalformedURLException, IOException{
       InputStream response = new URL(url).openStream();
       try (Scanner scanner = new Scanner(response)) {
    String responseBody = scanner.useDelimiter("\\A").next();
    System.out.println(responseBody);
    }
       return null;
   }
    
   public WeatherInfo getInfo(String country, String city){
       return null;
   }
    
   public class WeatherInfo{
       public String city;
       
       
       
   }
}
