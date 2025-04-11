import java.awt.Graphics;
import java.awt.Color;


public class Paddle {
   private int pX;
   private int pY;
   private int pH;
   private int pW;
   private Color col;
   

    public Paddle(int xCoor, int yCoor,int width, int height, Color color) {
        pX=xCoor;
        pY=yCoor;
        pH = height;
        pW = width; 
        col = color; 
        
    }

    // Accessor methods
    public int getX() {
        return pX;
    }

    public int getY() {
        return pY;
    }

    public int getWidth() {
        return pW;
    }

    public int getHeight() {
        return pH;
    }
    public void drawSelf(Graphics g)
    {
        g.setColor(col);
        g.fillRect(pX, pY, pW, pH);
        
    }
     public void moveUp() {
        if (pY > 0) { // Check if moving up would not go beyond the top 
            pY -= 30;
        }
    }

    // Method to move the paddle down
    public void moveDown(int Height) {
        if (pY + pH < Height) { // Check if moving down would not go beyond the bottom 
            pY += 30;
        }
    }

    // Method to move the paddle left
    public void moveLeft() {
        if (pX > 0) { // Check if moving left 
            pX -= 30;
        }
    }

    // Method to move the paddle right
    public void moveRight(int Width) {
        if (pX + pW < Width) { // Check if moving right 
            pX += 30;
        }
    }
}