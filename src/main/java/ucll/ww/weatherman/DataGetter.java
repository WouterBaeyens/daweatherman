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
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author Wouter
 */
public class DataGetter implements ForecastService{
    //user: wouter35 (not used)
    //key: 3e758fde9069c39c
    //example: http://api.wunderground.com/api/3e758fde9069c39c/conditions/q/CA/San_Francisco.json
    static String zaventem = "EBBR";
    static String urlBasis = "http://api.wunderground.com/api/3e758fde9069c39c/";
    static String forecastExtension = "forecast/q/";
    static String conditionsExtension = "conditions/q/";
    
    public static void main(String[] args){
        DataGetter dataGetter = new DataGetter();
        String exampleUrl = "http://api.wunderground.com/api/3e758fde9069c39c/forecast/q/CA/San_Francisco.json";
        try{
        dataGetter.getInfo(exampleUrl);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
   public String getInfo(String url) throws MalformedURLException, IOException{
       InputStream response = new URL(url).openStream();
       try (Scanner scanner = new Scanner(response)) {
        String responseBody = scanner.useDelimiter("\\A").next();
        return responseBody;
        } 
   }

    @Override
    public void loadWeather() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Observation getCurrentObservation(String country, String location) {
               String json = urlBasis + conditionsExtension + country + "/" + location + ".json"; 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Forecast> getForecast(String country, String location) {
        String json = urlBasis + forecastExtension + country + "/" + location + ".json";
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
 
}
