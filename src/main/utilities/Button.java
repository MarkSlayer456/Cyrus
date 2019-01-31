package main.utilities;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Mark
 */
public class Button implements Runnable {
    
    private Point pos;
    private double height;
    private double width;
    private Rectangle2D.Double rect;
    private boolean isActive;
    private Image image;
    
    /**
     * 
     * @param pos position of the top left corner of the button
     * @param height height of the button
     * @param width width of the button
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
    
    
    /**
     * All button creation will be executed here
     */
    public static void setup(Calculator calc) {
    	///// Calc /////
    	
    	Button plus = new Button(new Point(475, 150), 50, 50, false, null);
    	Button sub = new Button(new Point(475, 205), 50, 50, false, null);
    	Button mult = new Button(new Point(475, 260), 50, 50, false, null);
    	Button div = new Button(new Point(475, 315), 50, 50, false, null);
    	Button equal = new Button(new Point(475, 370), 50, 50, false, null);
    	
    	calc.addButton(plus);
    	calc.addButton(sub);
    	calc.addButton(mult);
    	calc.addButton(div);
    	calc.addButton(equal);
    	
    	/////////////////
    	
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
