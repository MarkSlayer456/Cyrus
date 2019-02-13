package main.managers;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

public class ImageLoader {
	
	public static ImageLoader imageManager = new ImageLoader();
	
	public static ImageLoader getInstance() {
		return imageManager;
	}
	
	public Image getImage(String path) {
	    Image image = null;
	    try {
	   	  String user = System.getProperty("user.name").toLowerCase();
	      URL imageUrl = new File("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + path).toURI().toURL();
	      image = Toolkit.getDefaultToolkit().getImage(imageUrl);
	    } catch (Exception e) {
	      System.out.println("ERROR: ImageLoader failed to load the image");
	    }
	    return image;
	  }

}
