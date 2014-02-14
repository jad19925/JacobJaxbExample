//Source baseline: https://code.google.com/p/jmingle/source/browse/trunk/src/org/oep/widgets/SpeechBubble.java
package jacob.jaxb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SpeechBubble {
	private static final int ARROW_HEIGHT = 7;
    private static final int ARROW_WIDTH = 4;
    private static final int PADDING = 8;
    
    /**
     * Draw an image inside a speech bubble
     * @param g, the graphics object
     * @param im, the image we want to draw
     * @param x, the x position of the bubble relative to the arrow
     * @param y, the y position of the bubble relative to the arrow
     */
//    public static void paintImage(Graphics g, Image im, int x, int y) {
//            if(g == null || im == null) return;     
//            SpeechBubble.drawBubble(g, x, y, im.getHeight() + PADDING, im.getWidth() + PADDING);
//            g.drawImage(im, x, y - ARROW_HEIGHT - im.getHeight() / 2 - PADDING / 2, Graphics.HCENTER | Graphics.VCENTER);
//    }

    /**
     * Draw a string in a speech bubble
     * @param g, the graphics object
     * @param s, the string to draw
     * @param f, the font we shall draw with
     * @param x, x-coordinate of the bubble relative to the arrow
     * @param y, y-coordinate of the bubble relative to the arrow
     */
//    public static void paintString(Graphics g, String s, Font f, int x, int y) {
//            if(g == null || s == null || f == null) return;
//            
//            int width = f.(s.toCharArray(), 0, s.length()) / 2;
//            String [] tokens = Utils.tokenize(s);
//            for(int i = 0; i < tokens.length; i++) {
//                    width = Math.max(width, f.charsWidth(tokens[i].toCharArray(), 0, tokens[i].length()));
//            }
//            String [] lines = Utils.addLineBreaks(s, f, width);
//            
//            int height = (lines.length * f.getHeight()) + PADDING;
//            width += PADDING;
//            
//            int origColor = g.getColor();
//            Font origFont = g.getFont();
//            
//            g.setFont(f);
//            
//            // We draw a bubble, and then the text on top of it
//            drawBubble(g, x, y, height, width);
//
//            
//            for(int i = 0; i < lines.length; i++) {
//                    g.drawString(lines[i], x, y - ARROW_HEIGHT - height + PADDING / 2 + i * f.getHeight(), Graphics.TOP | Graphics.HCENTER);
//            }
//            
//            g.setColor(origColor);
//            g.setFont(origFont);
//    }
    
    /**
     * Draw an empty speech bubble
     * @param g, the graphics object to paint to
     * @param x, the x position of the object (relative to the pointy part)
     * @param y, the y position of the object (relative to the pointy part)
     * @param height, the height of the body part
     * @param width, the width of the body part
     */
    public static void drawBubble(Graphics g, int x, int y, int height, int width) {
            // Save the graphics object color and font so that we may restore
            // it later
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
//            g.fillTriangle(x, y, x - ARROW_WIDTH / 2, y - ARROW_HEIGHT, x + ARROW_WIDTH / 2, y - ARROW_HEIGHT);
            g.fillPolygon(xcoords, ycoords, numTriangleCoords);
            g.drawLine(x - ARROW_WIDTH / 2 + 1, y - ARROW_HEIGHT, x + ARROW_WIDTH / 2 - 1, y - ARROW_HEIGHT);
            
            // This is the outline to the pointy part
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x - ARROW_WIDTH / 2, y - ARROW_HEIGHT);
            g.drawLine(x, y, x + ARROW_WIDTH / 2, y - ARROW_HEIGHT);
            
            // Restore the font and color
            g.setColor(origColor);
            g.setFont(origFont);
    }
}
