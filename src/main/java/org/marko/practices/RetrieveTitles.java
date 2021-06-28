package org.marko.practices;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * RetrieveTitles.java
 * 
 * Clase para Tarea Semana 1 de taller de microservicios.
 * @author Nicole Barra
 * 
 *
 */
public class RetrieveTitles {
  
  /**
   * url a buscar. no debe incluir num de página.
   */
  private static final String HTTPS_URL = "https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman";
  /**
   * string en formato json que se obtiene al leer el contenido de la pagina.
   */
  static String json  = "";
  /**
   * variable tipo url a utilizar en la conexión.
   */
  static URL url = null;
  /**
   * conexión a la pagina.
   */
  static HttpsURLConnection connection = null;
  /**
   * Constructor a la clase principal. Se inicializan las variables y se
   * genera el proceso de obtener los títulos de las películas.
   * 
   * @param args
   */
  public static void main(String[] args) {

    json = getJson(HTTPS_URL);
      
    if(isJSONValid(json)) {
      
      ArrayList<String> titles = null;
      
      JSONObject movies = new JSONObject(json);
      
      int numOfPages = movies.getInt("total_pages");
      
      if(numOfPages > 1) {
        
        titles = getAllPagedTitles(numOfPages);
        
      }
      else {
        titles = toTitlesList(movies);
      }
      
      printTitles(titles);
           
     }
    else {
      System.out.println("JSON invalid");
    }
  }
  
  /**
   * Regresa un string en formato json que se recibe un sitio web.
   * Genera la conexión https donde lee la primera linea que contiene el json.
   * 
   * @param urlString string con el url que se mandará a llamar para obtener el json.
   * @return primera linea en la pagina.
   */
  public static String getJson(String urlString) {
    String jsonRes = "";
    try {
      url = new URL(urlString);
      connection = (HttpsURLConnection) url.openConnection();
  } catch (MalformedURLException e) {
      e.printStackTrace();
  } catch (IOException e) {
      e.printStackTrace();
  }
  
  try (InputStream inputStream = connection.getInputStream();
      InputStreamReader isr = new InputStreamReader(inputStream);
      BufferedReader br = new BufferedReader(isr)) {
    
    jsonRes = br.readLine();
  } catch (Exception e) {
    // TODO: handle exception
  }
  
    return jsonRes;
  }
  
  /**
   * @param test string a la que se probará si tiene o no un formato json.
   * @return verdadero si la string tiene el formato json.
   */
  public static boolean isJSONValid(String test) {
    try {
        new JSONObject(test);
    } catch (JSONException ex) {
      
        try {
            new JSONArray(test);
        } catch (JSONException ex1) {
            return false;
        }
    }
    return true;
}

  
  /**
   * Llama a cada json paginado y obtiene las películas. 
   * Une las películas de todas las páginas en una sola lista.
   * 
   * @param numPages número de páginas en las que se divide el resultado del query.
   * @return lista con títulos de todas las películas en todas las páginas
   */
  public static ArrayList<String> getAllPagedTitles(int numPages){
    ArrayList<String> movieList = new ArrayList<String>();
    String temp_json = null;
    JSONObject temp_movies = null;
    for(int i = 1; i <= numPages; i++) {
      temp_json = getJson(HTTPS_URL + "&page=" + i);
      temp_movies = new JSONObject(temp_json);
      
      movieList.addAll(toTitlesList(temp_movies));
    }
    return movieList;
    
  }
  
  /**
   * Obtiene el título de cada película en el objeto json y lo añade a una lista.
   * 
   * @param movies objeto json donde se encuentra la data de las películas
   * @return lista del título de las películas en la data.
   */
  public static ArrayList<String> toTitlesList(JSONObject movies){
    ArrayList<String> titles = new ArrayList<String>();
    
    JSONArray arr = movies.getJSONArray("data");
    for (int i = 0; i < arr.length(); i++) {
      titles.add(arr.getJSONObject(i).getString("Title"));
    }
    
    return titles;
    
  }
  
  /**
   * Imprime los títulos de películas en la lista.
   * 
   * @param titles títulos de las películas
   */
  public static void printTitles(ArrayList<String> titles) {
    System.out.println("Movie Titles: \n");
    for (String str : titles)
    {               
         System.out.println(str);         
    }
  }

}
 