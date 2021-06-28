package org.marko.practices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpExample {

    //webpage url
	private static final String HTTPS_URL = "https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman&page=1";

	public static void main(String[] args) {

		URL url = null;
		HttpsURLConnection connection = null;
		try {
			url = new URL(HTTPS_URL);
			connection = (HttpsURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}

		//parenthesis are try-with-resources Statement. They close once finished
		try (InputStream inputStream = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(isr)) {

		    //prints each line of the website
			br.lines().forEach(System.out::println);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
