package main.utilities;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import main.managers.ImageLoader;

/**
 *
 * @author Mark
 */
public class Button implements Runnable {
    
    private Point pos;
    private final double height;
    private final double width;
    private Rectangle2D.Double rect;
    private final boolean isActive;
    private final Image image;
    
    /**
     * 
     * @param pos position of the top left corner of the button
     * @param height height of the button
     * @param width width of the button
     * @param isActive is the button currently on the screen
     * @param image the image to cover the button
     */
    public Button(Point pos, double height, double width, boolean isActive, Image image) {
        this.pos = pos;
        this.height = height;
        this.width = width;
        this.rect = new Rectangle2D.Double(this.pos.getX(), this.pos.getY(), this.height, this.width);
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
