//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

// TEACHER VERSION
// Adds 
//    bouncing to the Astronaut
//    more objects - moving (bouncing and wrapping) and stationary ones
//    background

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
   final int WIDTH = 1000;
   final int HEIGHT = 700;
   double shiftdown = HEIGHT/2;
   double shiftright = WIDTH/2;

   //Declare the variables needed for the graphics
   public JFrame frame;
   public Canvas canvas;
   public boolean even;
   public JPanel panel;
   public double z = (900* Math.random())-450;
   public double p = (900* Math.random())-450;
   
   public BufferStrategy bufferStrategy;

   public Point[] line; // declare array of points
   public Point[] line2;
   public double returnValue;
   public Font myfont= new Font("Times New Roman",1,50);

   public Point[] line3;

   // Main method definition
   // This is the code that runs first and automatically
   public static void main(String[] args) {
      BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
      new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
   }


   /*public  double sinCar(double a,double b){
      =a*b;
      return (y);
   }*/
   public  double lineTaxi(double b){
      returnValue = (b*(-0.004*b));
      return (returnValue);
   }

   /*public double colorChange(double a,double b){

   }*/
   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
   public BasicGameApp() {
      
      setUpGraphics();

      line= new Point[1000];
      line2 = new Point[1000];
      line3 = new Point[1000];// construct an array to hold points


      for(int a=0 ;a<1000; a++){
        //this is where we define the lines. To be clear, the shift right and -500 refer to where the parabola should start to be drawn

         line2[a] = new Point(a-500,lineTaxi(a+shiftright-1000));
         line3[a] = new Point(a , a );
         line[a]= new Point(a, (a*a));


      }
      //variable and objects

   
   
   }// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
   public void run() {
   
      //for the moment we will loop things forever.
      while (true) {
      
         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
      }
   }


   public void moveThings()
   {
      //calls the move( ) code in the objects

   
   }
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
      try {
         Thread.sleep(time);
      } 
      catch (InterruptedException e) {
      
      }
   }

   public void drawLine(){

   }

	//paints things on the screen using bufferStrategy
   private void render() {
      Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
      g.clearRect(0, 0, WIDTH, HEIGHT);


      // the next two lines of code draw the axis lines
      g.drawLine(0,(int)shiftdown,1600,(int)shiftdown);
      g.drawLine((int)shiftright,0,(int)shiftright,700);
      // sets default color to orange
      g.setColor(new Color(250,150,0));


      for(int a= 0;a<line2.length;a++){
         //this is where we draw the parabola/ function we are looking at and where we to the translation to put it in the center of the screen
        //g.fillRect((int)(line2[a].x + shiftright),(int)(line2[a].y +shiftdown),4,4);
        // if ((line2[a].y/line2[a].x)<(a)){
         //   g.setColor(new Color(37, 189, 121));
        // }
        // this is the if statement that checks fwhether the function is even and printsor  that information to the screen
/*
         if (line2[a].x == -line2[a].x) {
            even= true;
            if (even==true) {
               g.setColor(Color.pink);
               for(a = 0; a<line2.length; a++) {
                  g.fillRect((int) (line2[a].x + shiftright), (int) (line2[a].y + shiftdown), 4, 4);
               }
               g.setFont(myfont);
               g.drawString("This Function Is Even",200,300);

            } else if (even==false) {
               g.setColor(Color.cyan);
               for(a = 0; a<line2.length; a++) {
                  g.fillRect((int) (line2[a].x + shiftright), (int) (line2[a].y + shiftdown), 4, 4);
               }
               g.setFont(myfont);
               g.drawString("This Function Is Odd",200,300);
            }







           }

      */
         }
      //For next time: add smoother points and more automatic color switches
      for (int i = 0; i < line2.length-1; i++) {
         int x1 = (int) (line2[i].x + shiftright);
         int y1 = (int) (line2[i].y + shiftdown);
         int x2 = (int) (line2[i + 1].x + shiftright);
         int y2 = (int) (line2[i + 1].y + shiftdown);

         int diffy= y2-y1;
         int diffx = x2-x1;

         int slope = diffy/diffx;

         if (Math.abs(slope) <= 0.5){
            g.setColor(Color.RED);
         } else if (Math.abs(slope) <=2) {
            g.setColor(Color.MAGENTA);
         } else if (Math.abs(slope) <=5) {
            g.setColor(Color.BLUE);
         }
         g.drawLine(x1, y1, x2, y2);

         





      }
      /*g.setColor(new Color(0,200,100));
      for(int a= 0;a<line3.length;a++){
         g.fillRect((int)((line3[a].x)+shiftright),(int)(line3[a].y +shiftdown),4,4);
         // (int) trunkates decimal
      }*/
      g.dispose();
   
      bufferStrategy.show();
   }




   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }



}