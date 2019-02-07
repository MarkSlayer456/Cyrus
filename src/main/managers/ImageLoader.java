package main.managers;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class ImageLoader {
	
	public static ImageLoader imageManager = new ImageLoader();
	
	public static ImageLoader getInstance() {
		return imageManager;
	}
	
	public Image getImage(String path) {
	    Image image = null;
	    try {
	      URL imageUrl = ImageLoader.class.getResource(path);
	      image = Toolkit.getDefaultToolkit().getImage(imageUrl);
	    }
	    catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	    return image;
	  }

}
