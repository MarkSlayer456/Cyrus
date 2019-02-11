package main.utilities;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import main.managers.ImageLoader;

/**
 *
 * @author Mark
 */
public class Button implements Runnable {
    
	public static ImageLoader imageManager = ImageLoader.getInstance();
	
    private Point pos;
    private final double height;
    private final double width;
    private Rectangle2D.Double rect;
    private final boolean isActive;
    private final Image image;
    
    	//TODO add paths could be moved to imageLoader
  		public static final Image PLUS = imageManager.getImage("");
  		public static final Image SUB = imageManager.getImage("");
  		public static final Image MULTI = imageManager.getImage("");
  		public static final Image DIVIDE = imageManager.getImage("");
  		public static final Image EQUALS = imageManager.getImage("");
  		public static final Image ONE = imageManager.getImage("");
  		public static final Image TWO = imageManager.getImage("");
  		public static final Image THREE = imageManager.getImage("");
  		public static final Image FOUR = imageManager.getImage("");
  		public static final Image FIVE = imageManager.getImage("");
  		public static final Image SIX = imageManager.getImage("");
  		public static final Image SEVEN = imageManager.getImage("");
  		public static final Image EIGHT = imageManager.getImage("");
  		public static final Image NINE = imageManager.getImage("");
  		public static final Image ZERO = imageManager.getImage("");
    
    /**
     * 
     * @param pos position of the top left corner of the button
     * @param height height of the button
     * @param width width of the button
     * @param isActive is the button currently on the screen
     * @param image the image to cover the button
     */
    public Button(Point pos, double width, double height, boolean isActive, Image image, Frame frame) {
        this.pos = pos;
        this.height = height;
        this.width = width;
        this.rect = new Rectangle2D.Double(frame.getWidth() - this.pos.getX(), frame.getHeight() - this.pos.getY(), this.width, this.height);
        this.isActive = isActive;
        this.image = image;
    }
    
    @Override
    public void run() {
        doLogic();
    }
    /**
     * Runs the logic for the button object;
     * checks to see if user has clicked the button
     */
    public void doLogic() {
        //TODO gonna have to create mouse listener before this can be completed
    }
    
    ///// setters /////
    // Don't need to change the height and width ever so setters are not useful
    public void setPos(Point point) {
        this.pos = point;
    }
        
    ///// getters /////
    
    public Point getPos() {
        return this.pos;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public Rectangle2D.Double getRect() {
        return this.rect;
    }
    
    public boolean isActive() {
    	return this.isActive;
    }
    
    
}
