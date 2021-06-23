package org.marko.practices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpExample {

	private static final String HTTPS_URL = "https://en.wikipedia.org/wiki/FC_Bayern_Munich";

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

		try (InputStream inputStream = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(isr)) {

			br.lines().forEach(System.out::println);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
