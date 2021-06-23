package org.marko.practices;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

public class HttpExample {

	private static final String HTTPS_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/1024px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png";
	private static final String PATH = "D:/";
	
	public static void main(String[] args) {
		
		URL url = null;
		HttpsURLConnection connection = null;
		File file = null;
		BufferedImage bufferedImage = null;
		
		try {
			url = new URL(HTTPS_URL);
			connection = (HttpsURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			file = File.createTempFile("tmp", ".png", new File(PATH));
			file.deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		try (InputStream inputStream = connection.getInputStream();) {
			bufferedImage = ImageIO.read(inputStream);
			ImageIO.write(bufferedImage, "png", file);
			System.out.println("Image's Path:"+file.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Waiting time to see the temporary file at the image's path.
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
