import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.util.ArrayList;
import java.applet.*;
//imports for drawing Images
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;





public class LearningGraphics extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    private int rX;
    private int rY;
    private int rW;
    private int rH;
    private int cX;
    private int cY;
    private int diam; 
    private int cVx;
    private int cVy; 
    private Paddle p1;//paddle
    private Paddle p2;//paddle
    private Ball b;// ping pong ball
    private int Player1;//player score
    private int Player2; //playrer score 2
    private boolean showInstructions;//to show instructions
    private AudioClip Song;// song to listen to while you play 

    
    

    
    
    
    
    
    //Default Constructor
    public LearningGraphics()
    {
        //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 500;
        rX=300;
        rY=300;
        rW = 50;
        rH= 100; 
        cX = 500;
        cY=300;
        diam =  70;
        cVx = 5;
        cVy = 5;
        showInstructions=false;//will be false until user hits i key 
       
        

        p1 = new Paddle (50,150,50,100,Color.CYAN);
        p2 = new Paddle(900,150,50,100,Color.ORANGE);
        b = new Ball(300,300,50,Color.GREEN);
        Player1 = 0;
        Player2 = 0;
        Song = Applet.newAudioClip(getClass().getClassLoader().getResource("New Recording 36.wav"));

        

        
        
       
        
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Learning Graphics"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui
        /*If after you finish everything, you can declare your buttons or other things
        *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
        */
        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
        Song.loop();
    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e)
    {
        //getting the key pressed
        int key = e.getKeyCode();
        System.out.println(key);
        //move paddle on the right 
        if(key==38){
           p2.moveUp();
        }
        else if(key==40){
            p2.moveDown(HEIGHT);
        }
        else if(key==37){
            
            if(p2.getX()>540){
                p2.moveLeft();
            }
        }
        else if(key==39){
            p2.moveRight(WIDTH);
        }
        //moves left paddle 
        else if(key==87){
           p1.moveUp();
        }
        else if(key==83){
            p1.moveDown(HEIGHT);
        }
        else if(key==65){
            p1.moveLeft();
        }
        else if(key==68){
            p1.moveRight(WIDTH/2-25);
        }
        else if(key==73){
            showInstructions=true;//to get instructions 
        }
        else if(key==79){
            showInstructions = false;//to exit out of instructions
        }
        else if(key==32&&!gameRun()){
            runAgain();
        }
        
    }
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
       
        Graphics2D g2d = (Graphics2D)g;
                
        //font I
        Font font = new Font("Arial", Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.BLACK);
        
        
        //background 
        Image background = new ImageIcon(Paddle.class.getResource("360_F_491524667_qcNdTDwulwE2ZIxzI0zh2JAQH2Z8lVfr.jpeg")).getImage();
        
        g2d.drawImage(background, 0, 0,WIDTH, HEIGHT, this);
        
        //net
        g.setColor(Color.WHITE);
        g.fillRect(500, 0, 20, 500);
        
        // to show the instructions to new player 
        if(showInstructions==false){
            Font f1 = new Font("Times New Roman", Font.BOLD, 20);
            g.setFont(f1);
            g.setColor(Color.BLUE);
            g.drawString("Press i to show instructions",100,400);
        }
        p1.drawSelf(g);
        p2.drawSelf(g);
        b.drawSelf(g);
        drawScoreboard(g);
        
        if(Player1==11){//to show player 1 won
            Font f2 = new Font("Times New Roman", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.YELLOW);
            g.drawString("Player 1 has WON!! ",WIDTH/2-200,HEIGHT/2);
            
            
        }
        if(Player2==11){//to show player 2 won 
            Font f2 = new Font("Times New Roman", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.YELLOW);
            g.drawString("Player 2 has WON!! ",WIDTH/2-200,HEIGHT/2);
            
        }
        
        if(showInstructions==true){//Specific instructions that will appear 
            Font f3 = new Font("Times New Roman", Font.BOLD, 20);
            g.setFont(f3);
            g.setColor(Color.GREEN);
            g.drawString("First player to get 11 points wins! Press arrow keys continually to  move the right paddle",100,400);

            g.setFont(f3);
            g.setColor(Color.GREEN);
            g.drawString("Press ASWD keys continually to move the paddle on the left",100,450);
            
            g.setFont(f3);
            g.setColor(Color.GREEN);
            g.drawString("Press o to exit instructions.",100,490);
            
            g.setFont(f3);
            g.setColor(Color.GREEN);
            g.drawString("Press SPACE BAR to play multiple times",400,490);
        }
    }
    public void loop()
    {
        //task 5
        cX += cVx; 
        cY += cVy;
        //making the autonomous circle move
        int nextX = cX+cVx;
        int nextY = cY+cVy; 
        if (nextY + diam >= HEIGHT || nextY <= 0) {
            cVy *= -1;
        }
        if(nextX+diam>=WIDTH||nextX<=0){
            cVx*=-1;
        }
        //handling when the circle collides with the edges
        
        //handling the collision of the circle with the rectangle
        
        
        
        b.act(WIDTH,HEIGHT);
        b.handleCollision(p1);
        b.handleCollision(p2);
        if (b.getX() <= 0) {// if ball gets past first paddle 
        Player2++; // increment Player 2 score
        b.resetBall();
        
        }
        else if (b.getX() + b.getDiam() >= WIDTH) {// if ball gets past other paddle 
            Player1++; //increment player 1 score 
            b.resetBall();
        }
        
       

        //Do not write below this
        repaint();
    }
    
    public void keyTyped(KeyEvent e)
    {
    }
    public void keyReleased(KeyEvent e)
    {
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {

    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
        
    }
    public void mouseMoved(MouseEvent e)
    {
    }
    public void mouseDragged(MouseEvent e)
    {
    }
    
    public void start(final int ticks){
        
        Thread gameThread = new Thread(){
            public void run(){
            while(gameRun()){//relies on my gameRun method 
            loop();
            try{
            Thread.sleep(1000 / ticks);
            }catch(Exception e){
            e.printStackTrace();
            }
            }
            }
            };
            gameThread.start();
    }
    
    
    public static void main(String[] args)
    {
        LearningGraphics g = new LearningGraphics();
        g.start(60);
        
       
        
    }
    
    //task 6 
    public double distance(int x1, int y1, int x2, int y2 ){//distance formula 
        int diff1 = x2-x1;
        int diff2 = y2-y1;
        double sq1 = Math.pow(diff1,2);
        double sq2 = Math.pow(diff2,2);
        double sum = sq1+sq2;
        double output = Math.sqrt(sum);
        return output; 
        
    } 
    
    private void drawScoreboard(Graphics g) {//My scoreboard for player 1 and player 2 
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Player 1: " + Player1, 20, 30);
        g.drawString("Player 2: " + Player2, getWidth() - 150, 30);
        
    }
    private boolean gameRun() {//runs game until one player reaches 11 points
        boolean gameRun = true;
        if(Player1>=11||Player2>=11){
            gameRun = false;
            Song.stop();
        }
        return gameRun;
        
    }
//    private void resetBall() {
//        // Reset the ball to its initial position
//        b = new Ball(300,(int) (Math.random()*450), 50, Color.GREEN);
//        
//    }
    private void runAgain() { //to let the user play multiple times
        Player1 = 0;
        Player2 = 0;
        Song.loop();
        start(60);//restarts game
        repaint();//repaints everything 
        
    }
    
    
}