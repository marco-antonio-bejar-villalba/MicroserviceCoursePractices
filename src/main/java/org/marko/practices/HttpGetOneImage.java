package org.marko.practices;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

public class HttpGetOneImage {

    //webpage url
	private static final String FILE_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg/800px-FC_Bayern_M%C3%BCnchen_logo_%282017%29.svg.png";
	
	public static void main(String[] args) {

		URL url = null;
		BufferedImage image = null;
		try {
			url = new URL(FILE_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}

		try  {
		    
		  image = ImageIO.read(url);
		  
		  ImageIO.write(image, "png",new File("bayern.png"));
		  System.out.println("Image has been printed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
