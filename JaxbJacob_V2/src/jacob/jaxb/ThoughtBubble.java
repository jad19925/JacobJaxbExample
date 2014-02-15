//Source baseline: https://code.google.com/p/jmingle/source/browse/trunk/src/org/oep/widgets/SpeechBubble.java
package jacob.jaxb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class ThoughtBubble extends JLabel {
	private int ARROW_HEIGHT = 7;
    private int ARROW_WIDTH = 4;
    private int PADDING = 8;
    private int xPos;
    private int yPos;
    private int widthC;
    private int heightC;
    private String textC;
    private Font fontC = new Font("Comic Sans MS", Font.BOLD, 15);
    public static enum PointDirection{LEFT_DOWN,CENTER_DOWN,RIGHT_DOWN};
    private PointDirection directionC = PointDirection.LEFT_DOWN;
    
    public ThoughtBubble()
    {
    	super();
    	textC = "";
    }
    
    public ThoughtBubble(String text)
    {
    	super();
    	textC = text;
    }
    
    public void setPointDirection(PointDirection p)
    {
    	directionC = p;
    }
    
    /**
     * Set the bounds of the label capturing the necessary variables for drawing the bubble
     * 
     */
    public void setBounds(int x, int y, int width, int height)
    {
    	xPos = x;
    	yPos = y;
    	widthC = width;
    	heightC = height;
    	ARROW_HEIGHT = widthC/8;
    	ARROW_WIDTH = widthC/10;
    	PADDING = heightC/2;
    	super.setBounds(x, y, width, height);
    }
    
    public void setFont(Font font)
    {
    	fontC = font;
    	super.setFont(font);
    }

    /**
     * Draw a string in a speech bubble
     * @param text, the string to draw
     */
    private void paintString(String text) {
    	JLabel innerLabel = new JLabel("<html><p>" + text + "</p></html>");
    	innerLabel.setBounds(PADDING, PADDING/2, widthC-PADDING*2, heightC-PADDING-ARROW_HEIGHT);
    	innerLabel.setFont(fontC);
    	innerLabel.setForeground(Color.BLACK);
    	innerLabel.setBackground(Color.WHITE);
    	innerLabel.setOpaque(true);
    	innerLabel.setHorizontalAlignment(JLabel.CENTER);
    	innerLabel.setVerticalAlignment(JLabel.TOP);
		add(innerLabel);
    }
    
    /**
     * Draw an empty speech bubble with the point in the center
     * @param g, the graphics object to paint to
     * @param x, the x position of the object (relative to the pointy part)
     * @param y, the y position of the object (relative to the pointy part)
     * @param height, the height of the body part
     * @param width, the width of the body part
     */
    private void drawBubblePointCenter(Graphics g, int x, int y, int width, int height) {
        // Save the graphics object color and font so that we may restore it later
        Color origColor = g.getColor();
        Font origFont = g.getFont();
        int numTriangleCoords = 3;
        int[] xcoords = new int[numTriangleCoords];
        int[] ycoords = new int[numTriangleCoords];
        xcoords[0] = x;
        ycoords[0] = y;
        xcoords[1] = x - ARROW_WIDTH / 2;
        ycoords[1] = y - ARROW_HEIGHT;
        xcoords[2] = x + ARROW_WIDTH / 2;
        ycoords[2] = y - ARROW_HEIGHT;

        // Draw the base shape -- the rectangle the image will fit into as well as its outline
        g.setColor(Color.WHITE);
        g.fillRoundRect(x - width / 2, y - height - ARROW_HEIGHT, width, height, PADDING * 2, PADDING * 2);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x - width / 2, y - height - ARROW_HEIGHT, width, height, PADDING * 2, PADDING * 2);
        
        // Next is to draw the pointy part that indicates who is speaking
        g.setColor(Color.WHITE);
        g.fillPolygon(xcoords, ycoords, numTriangleCoords);
        g.drawLine(xcoords[1] + 1, ycoords[1], xcoords[2] - 1, ycoords[2]);
        
        // This is the outline to the pointy part
        g.setColor(Color.BLACK);
        g.drawLine(x, y, xcoords[1], ycoords[1]);
        g.drawLine(x, y, xcoords[2], ycoords[2]);
        
        // Restore the font and color
        g.setColor(origColor);
        g.setFont(origFont);
    }
    
    /**
     * Draw an empty speech bubble with the point on the left
     * @param g, the graphics object to paint to
     * @param x, the x position of the object (relative to the pointy part)
     * @param y, the y position of the object (relative to the pointy part)
     * @param height, the height of the body part
     * @param width, the width of the body part
     */
    private void drawBubblePointLeft(Graphics g, int x, int y, int width, int height) {
        // Save the graphics object color and font so that we may restore it later
        Color origColor = g.getColor();
        Font origFont = g.getFont();
        int numTriangleCoords = 3;
        int[] xcoords = new int[numTriangleCoords];
        int[] ycoords = new int[numTriangleCoords];
        xcoords[0] = x;
        ycoords[0] = y;
        xcoords[1] = x + ARROW_WIDTH;
        ycoords[1] = y - ARROW_HEIGHT;
        xcoords[2] = x + ARROW_WIDTH * 2;
        ycoords[2] = y - ARROW_HEIGHT;

        // Draw the base shape -- the rectangle the image will fit into as well as its outline
        g.setColor(Color.WHITE);
        g.fillRoundRect(x+5, y - height - ARROW_HEIGHT+5, width-10, height-10, height-10, height-10);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x+5, y - height - ARROW_HEIGHT+5, width-10, height-10, height-10, height-10);
        //create an array of points along the edge of the rounded rectangle and draw arcs between the points somehow?
        g.drawRoundRect(x, y - height - ARROW_HEIGHT, width, height, height, height);
        /*
         * 
         */
        g.drawArc(x+height/2-28, 8, 15, 15, 30, 210);
        g.drawArc(x+height/2-15, 0, 15, 15, 0, 210);
        g.drawArc(x+height/2, 0, 15, 15, 0, 180);
        g.drawArc(x+height/2+15, 0, 15, 15, 0, 180);
        g.drawArc(x+height/2+30, 0, 15, 15, 0, 180);
        
        //draw little circles indicating who is thinking
        g.setColor(Color.WHITE);
        g.fillArc(x, y-6, 5, 5, 0, 360);
        g.fillArc(x+5, y-14, 8, 8, 0, 360);
        g.fillArc(x+13, y-24, 10, 10, 0, 360);
        
        //outline the circles
        g.setColor(Color.BLACK);
        g.drawArc(x, y-6, 5, 5, 0, 360);
        g.drawArc(x+5, y-14, 8, 8, 0, 360);
        g.drawArc(x+13, y-24, 10, 10, 0, 360);
        
        // Restore the font and color
        g.setColor(origColor);
        g.setFont(origFont);
    }
    
    /**
     * Draw an empty speech bubble with the point on the right
     * @param g, the graphics object to paint to
     * @param x, the x position of the object (relative to the pointy part)
     * @param y, the y position of the object (relative to the pointy part)
     * @param height, the height of the body part
     * @param width, the width of the body part
     */
    private void drawBubblePointRight(Graphics g, int x, int y, int width, int height) {
        // Save the graphics object color and font so that we may restore it later
        Color origColor = g.getColor();
        Font origFont = g.getFont();
        int numTriangleCoords = 3;
        int[] xcoords = new int[numTriangleCoords];
        int[] ycoords = new int[numTriangleCoords];
        xcoords[0] = x;
        ycoords[0] = y;
        xcoords[1] = x - ARROW_WIDTH;
        ycoords[1] = y - ARROW_HEIGHT;
        xcoords[2] = x - ARROW_WIDTH * 2;
        ycoords[2] = y - ARROW_HEIGHT;

        // Draw the base shape -- the rectangle the image will fit into as well as its outline
        g.setColor(Color.WHITE);
        g.fillRoundRect(x-width, y - height - ARROW_HEIGHT, width, height, PADDING * 2, PADDING * 2);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x-width, y - height - ARROW_HEIGHT, width, height, PADDING * 2, PADDING * 2);
        
        // Next is to draw the pointy part that indicates who is speaking
        g.setColor(Color.WHITE);
        g.fillPolygon(xcoords, ycoords, numTriangleCoords);
        g.drawLine(xcoords[1] - 1, ycoords[1], xcoords[2] + 1, ycoords[2]);
        
        // This is the outline to the pointy part
        g.setColor(Color.BLACK);
        g.drawLine(x, y, xcoords[1], ycoords[1]);
        g.drawLine(x, y, xcoords[2], ycoords[2]);
        
        // Restore the font and color
        g.setColor(origColor);
        g.setFont(origFont);
    }
    
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	g.setColor(Color.BLACK);
    	switch(directionC) {
    		case LEFT_DOWN:
    			drawBubblePointLeft(g,0,heightC,widthC-2,heightC-ARROW_HEIGHT-1);
    			break;
    		case CENTER_DOWN:
    			drawBubblePointCenter(g,widthC/2,heightC,widthC-2,heightC-ARROW_HEIGHT-1);
    			break;
    		case RIGHT_DOWN:
    			drawBubblePointRight(g,widthC,heightC,widthC-2,heightC-ARROW_HEIGHT-1);
    			break;
    		default:
    			break;
    	}
    	paintString(textC);
    }
}
