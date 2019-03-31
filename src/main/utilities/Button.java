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
    private String text;
    
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
     * Creates a Button
     * @param pos position of the top left corner of the button
     * @param height height of the button
     * @param width width of the button
     * @param isActive is the button currently on the screen
     * @param image the image to cover the button
     */
    public Button(Point pos, double width, 
    		double height, boolean isActive, Image image, Frame frame, String text) {
        this.pos = pos;
        this.height = height;
        this.width = width;
        this.rect = new Rectangle2D.Double(frame.getWidth() - this.pos.getX(), frame.getHeight() - this.pos.getY(), this.width, this.height);
        this.isActive = isActive;
        this.image = image;
        this.text = text;
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
	/**
	 * Sets the position of the button
	 * @param point - The point of the location you want to use for the new position
	 */
    public void setPos(Point point) {
        this.pos = point;
    }
        
    ///// getters /////
    /**
	 * Gets position of the button
	 * @return - The position of the button
	 */
    public Point getPos() {
        return this.pos;
    }
    /**
	 * Gets height
	 * @return - The height of the button
	 */
    public double getHeight() {
        return this.height;
    }
    /**
	 * Gets width
	 * @return - The width of the button
	 */
    public double getWidth() {
        return this.width;
    }
    /**
	 * Gets rectangle
	 * @return - The rectangle that is the button
	 */
    public Rectangle2D.Double getRect() {
        return this.rect;
    }
    /**
	 * Checks to see if the button is active, can be seen and interacted with
	 * @return - True if the current button is active; false otherwise
	 */
    public boolean isActive() {
    	return this.isActive;
    }
    
    /**
     * Gets text on button 
     * @return - The text displayed on the button 
     */
    public String getText() {
    	return this.text;
    }
    
    
}
