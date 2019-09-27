import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	//size of jframe
	int screen_width 	= 900;
	int screen_height 	= 800;
	double run = 0;
	double rise = 0;
	
	
	//primitives - int, boolean, double
	//why are they called primitives?
	//store one thing at a time!
	
	//variables (primitive) for player
	int w = 20; //initial size of our player (main cell)
	int pX = screen_width/2 - w/2;
	int pY = screen_height/2 - w/2;
	int max_speed = 8;

	//cells - enemies
	int numEnemies = 100;
	//left-side is called a reference to an array
	//right-side is actually making the array
	int[] enemyXs = new int[numEnemies]; //all initialized to 0
	int[] enemyYs = new int[numEnemies]; //all initialized to 0
	int[] enemyWs = new int[numEnemies]; //size of enemies
	Color [] enemyCs = new Color [numEnemies]; //colors of enemies
	
	//need a set of arrays to track enemy velocities in x and y direction
	int[] enemyeVx = new int[numEnemies];
	int[] enemyeVy = new int[numEnemies];
	
	int centerpX = pX + w/2; //for player
	int centerpY = pY + w/2; //for player
	
	
	// reading a val from a 1d array
	// System.out.print( x[0]); //reading value
	// x[0] = 5; //writing is similar to regular variables but now you have to specify WHERE
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//paint the player
		g.setColor(Color.ORANGE);
		g.fillOval(pX,  pY,  w,  w);
		
		for(int i = 0; i < numEnemies; i++){
			g.setColor(enemyCs[i]);
			g.fillOval(enemyXs[i], enemyYs[i], enemyWs[i], enemyWs[i]);
			
		}
		
		
	}//end of paint method - put code above for anything dealing with drawing -
	
	Font font = new Font ("Courier New", 1, 50);
	
	
	
	//write code here if you're updating variables
	
	public void update() {
		pX += pVx; //velocity components change the position of the player
		pY += pVy;
		
		centerpX += pVx;
		centerpY += pVy;
		
		//update all x values based on their respective velocities
		for(int i = 0; i < enemyXs.length; i++){
			enemyXs[i] += enemyeVx[i]; //x position changes with x velocity
			enemyYs[i] += enemyeVy[i]; //y position changes with y velocity
		}
		
		
		for(int i = 0; i < enemyXs.length; i++){
			int centereXs = enemyXs[i] + (enemyWs[i]/2);
			int centereYs = enemyYs[i] + (enemyWs[i]/2);
			int radiusp = w/2;
			int radiuse = enemyWs[i]/2;
			double d = Math.sqrt(Math.pow(centerpY - centereYs, 2) + Math.pow(centerpX - centereXs, 2));
			
			if(d < radiusp + radiuse){
				w += 2; //player width increases

				//make it so that the enemies move off the screen when eaten; code is similar in part to the velocity randomness
				enemyXs[i] = 5000;
				enemyYs[i] = 5000;
				
			}
			
	
			/*System.out.println(centereXs);
			System.out.println(centereYs);
			System.out.println(radiusp);
			System.out.println(radiuse);
			System.out.println(d);
			System.out.println(w);
			*/
			
		}
			
		
		
		
		/*Collision Setup:
		 *int centerpX = pX + w/2; //for player
		 *int centerpY = pY + w/2; //for player
		 *make a for loop to go through all the enemy centers 
		 *
		 * set distance (d) = distance between two centers - use distance formula
		 * if distance <= r1 + r2 (radius of each circle) then collision is happening
		 * if collision is happening, increase width of player linearly
		 * 
		 * 
		 *
		 */
		
		
		
		
		
		
		
		
		
		
	}//end of update method - put code above for any updates on variable
		
	
	//==================code above ===========================
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}
	
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	public Driver(){
		JFrame f = new JFrame();
		f.setTitle("Pong");
		f.setSize(screen_width, screen_height);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);

		//this special "method" is called the Constructor
		//initialized structures here!
		
		//need a loop to visit all the possible addresses in my arrays
		//first spot in an array is addressed by index 0 
		int counter = 0; //starting address to visit
		while(counter < enemyXs.length){ //
			
			//int rand = (int) (Math.random() * (range + 1) + min;
			int randX = (int) (Math.random() * (screen_width) + 0); //naive approach, allows going off the screen
			int randY = (int) (Math.random() * (screen_height) + 0);
			
			int eVx = (int) (Math.random() * (15) - 7);
			int eVy = (int) (Math.random() * (15) - 7);
			
			if(eVx == 0){
				int posneg = (int) (Math.random() * (2));
				if(posneg == 0){
					eVx = (int) (Math.random() * (7) - 7);
				}
				if(posneg == 1) {
					eVx = (int) (Math.random() * (6) + 1);
				}
			}
			
			if(eVy == 0){
				int posneg = (int) (Math.random() * (2));
				if(posneg == 0){
					eVy = (int) (Math.random() * (7) - 7);
				}
				if(posneg == 1) {
					eVy = (int) (Math.random() * (6) + 1);
				}
			}
			
			
			//writing to an array location
			//call the name of the array with bracket notation
			enemyXs[counter] = randX;
			enemyYs[counter] = randY;
			
			enemyeVx[counter] = eVx;
			enemyeVy[counter] = eVy;
			
			//width
			enemyWs[counter] = 20;
			//random colors
			
			int r = (int) (Math.random() * (256));
			int g = (int) (Math.random() * (256));
			int b = (int) (Math.random() * (256));
			
			Color thisColor = new Color(r, g, b);
			enemyCs[counter] = thisColor;
			
			//increment while loop
			counter++;
			
		}
		
		
		
		f.add(this);
		
		
		t = new Timer(17,this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		
		//pressed
		if(e.getKeyCode()==32) { //if spacebar is pressed, then check if width of player ball is > 15; if so, then the ball's size can be reduced
			if(w > 15) {
				w--;
			}
				
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
 

	
	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		pVx = 0;
		pVy = 0; //turn off moving on mouseReleased
		
		
	}

	
	
	//velocity variables
	int pVx = 0;
	int pVy = 0; 
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
		//System.out.println(arg0.getX()+" : "+arg0.getY());
		//System.out.println(pX+" : "+pY);
		
		//based on where they're clicking, figure out how to set
		//the velocity variables so it moves in that direction!!!!!
		//pVx = arg0.getX()-pX; //suggestion #1
		//pVy = arg0.getY()-pY; //suggestion #1
	
		run = arg0.getX() - pX;
		rise = arg0.getY() - pY;
		double slope = rise / run;
		double z = Math.sqrt((run * run) + (rise * rise));
		
		if(z > max_speed){
			double scale = z / 8;
			run /= scale;
			rise /= scale;
		}
	
		pVx = (int) run;
		pVy = (int) rise;
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
