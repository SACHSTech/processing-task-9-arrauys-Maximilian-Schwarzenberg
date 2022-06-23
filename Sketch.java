import processing.core.PApplet;

public class Sketch extends PApplet {

   // Declare variables
  float playerX = width;
  float playerY = height;
  float[] circleY = new float[25];
  int intLives = 3;
  boolean boolLiveslost = false;
  boolean[] boolSnowVis = new boolean[25];
  boolean boolMouseClick = false;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
     // for loop to indicate the Y variable of the snowballs
    for (int i = 0; i < circleY.length; i++) { 
      circleY[i] = random(height);
      boolSnowVis[i] = true;
    }

  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0); // Black
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
	// sample code, delete this stuff
    background(0);

    fill(0, 0, 139); // Blue
    ellipse(playerX, playerY, 25, 25); // Draws the player
    
    for (int i = 0; i < circleY.length; i++) {
      float circleX = width * i / circleY.length; // X variable for the snowballs
      
      if (boolSnowVis[i]) { // Draws the snowballs if it is supposed to be visible
        fill(255); // White
        ellipse(circleX, circleY[i], 25, 25);
      }

      
       // Changes speed of the descent of the snowballs
      if (keyPressed) {
        if (keyCode == UP) {
           // Slows down snowfall
          circleY[i]-=1;
        }
        else if (keyCode == DOWN) {
           // Speeds up snowfall
          circleY[i]+=4;
        }
      }
      
       // Normal speed snowfall
      circleY[i]+=2;

       // Teleports the snowballs back to the top
      if (circleY[i] > height+(25/2)) {
        circleY[i] = 0;
      }

       // Player detection if they collide with the snowfall
      if (playerX >= circleX-12.5 && playerX <= circleX+12.5 && playerY >= circleY[i]-12.5 && playerY <= circleY[i]+12.5 && boolSnowVis[i]) 
      {
         // If they collide takes one life off and makes the snowball invisible
        intLives -= 1;
        boolSnowVis[i] = false;
      }
      

       // Clicking on the snowballs make them go invisible
      if (mouseX >= circleX-12.5 && mouseX <= circleX+12.5 && mouseY >= circleY[i]-12.5 && mouseY <= circleY[i]+12.5 && boolLiveslost == false && boolMouseClick == true) 
      {
        boolSnowVis[i] = false;
      }

    }

     // Restrictions so the player doesnt go off screen
    if (playerX < 0) {
      playerX = 0+(25/2);
    }
    if (playerY < 0) {
      playerY = 0+(25/2);
    }
    if (playerX > width) {
      playerX = width-(25/2);
    }
    if (playerY > height) {
      playerY = height-(25/2);
    }

    
     // Lives display
    if (intLives == 3) {
      fill(173, 216, 230); // Light blue
      rect(385, 5, 10, 10);
      rect(370, 5, 10, 10);
      rect(355, 5, 10, 10);
    }
    if (intLives == 2) {
      fill(173, 216, 230); // Light blue
      rect(385, 5, 10, 10);
      rect(370, 5, 10, 10);
    }
    if (intLives == 1) {
      fill(173, 216, 230); // Light blue
      rect(385, 5, 10, 10);
    }
    if (intLives <= 0) {
      background(255); // White
    }

  }
  
  // other methods

  /**
  * Detects if a key on the keyboard is clicked
  * @return if further keys are clicked it executes a condition
  */
  public void keyPressed() {
     // Shift the player
    if (key == 'w') {
      playerY-=4;
    }
    if (key == 's') {
      playerY+=4;
    }
    if (key == 'a') {
      playerX-=4;
    }
    if (key == 'd') {
      playerX+=4; 
    }
    
  }

  /**
  * Detects if mouse is pressed down
  * @return changes boolMouseClick to true
  */
  public void mousePressed() {
    boolMouseClick = true;
  }

  /**
  * Detects if mouse is released up
  * @return changes boolMouseClick to false
  */
  public void mouseReleased() {
    boolMouseClick = false;
  }
  
}