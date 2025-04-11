import java.applet.*;
import java.awt.Graphics;
import java.awt.Color;



public class Ball
{
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int diam;
    private Color col;
    private AudioClip hit;
    

    public Ball(int xCoor, int yCoor, int d, Color c)
    {
        x=xCoor;//coorinates
        y=yCoor;
        diam = d; 
        col=c;
        vx = 7;
        vy = 7;
        hit = Applet.newAudioClip(getClass().getClassLoader().getResource("5b92-3868-43bc-880a-7094b0534a0a.wav"));// hit sound effect
       

        
        
    }
    public String toString()
    {
        return "location: "+"("+x+" , "+y+")"+"\n"+"diameter: "+diam;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y; 
    }
    public int getDiam()
    {
        return diam; 
    }
    public int getvX(){
        return vx;
    }
    public int getvY(){
        return vx;
    }
    private double distance(int x1, int y1, int x2, int y2)
    {
        int diff1 = x2-x1;
        int diff2 = y2-y1;
        double sq1 = Math.pow(diff1,2);
        double sq2 = Math.pow(diff2,2);
        double sum = sq1+sq2;
        double output = Math.sqrt(sum);
        return output; 
    }
    public int getCenterX()
    {
        return x+(diam/2);
    }

    public int getCenterY()
    {
        return y+(diam/2);
    }
    public void act(int w, int h)
    {
        // Get the next x and y coordinates
        int nextX = x + vx;
        int nextY = y + vy;
        //if-statements to handle the Bubble bouncing off of the 4 walls
        if (nextY + diam >= h || nextY <= 0) {
            vy *= -1; 
        }   
        x += vx;
        y += vy;
        
        
    
    
    }
    public void drawSelf(Graphics g)
    {
        //creates ball appearance 
        g.setColor(col);
        g.fillOval(x, y, diam, diam);
        
    }
    public void speedup(){
        vx*=3;
        vy*=3;
    }
    public void handleCollision(Paddle p)//when ball hits a paddle 
    {
        
        boolean output = false;
        int radius = diam/2;
        int nextY = y+vy;
        int nextX = x+vx;
        int centerX = (2*nextX+diam)/2;
        int centerY = (2*nextY+diam)/2;
        
        for(int i = p.getX(); i<=p.getX()+p.getWidth();i++){
            for(int j = p.getY();j<=p.getY()+p.getHeight();j++){
                double distance = distance(centerX,centerY,i,j);
                
                if(distance<radius){
                    output = true;
                    
                   
                }   
            }    
        }
        if(output==true){//ball will move in a different direction 
            vx*=-1;
            vy*=-1;
            hit.play();
            
        }
        
    }
    public void resetBall(){
        x=500;
       y= (int) (Math.random()*450);
        
    }
    
    
}
