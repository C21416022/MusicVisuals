package c21716601;

import processing.core.PApplet;

public class emil1 {

    rockstar emil; // Declare a "rockstar" object named "emil"

    float smallSphereSize = 0; // Declare and initialize the initial size of the small sphere
    float smallSphereAngle = 0; // Declare and initialize the initial angle of rotation for the small sphere

    public emil1(rockstar emil) {
        this.emil = emil; // Constructor that takes a "rockstar" object as a parameter and sets it to the
                          // "emil" variable
    }

    public void render() {
        emil.noFill(); // Set fill color to transparent
        emil.camera(0, 100, 200, 0, 0, 0, 1, 0, 0); // Set the camera position
        emil.translate(0, 0, 0); // Translate the scene
        emil.strokeWeight(4); // Set stroke weight

        float[] stars = new float[500]; // Declare an array of 500 float values named "stars"

        // Populate the "stars" array with random values
        for (int i = 0; i < stars.length; i++) {
            stars[i] = emil.random(-emil.width, emil.width);
        }

        // Render stars
        emil.stroke(255, 255, 255); // Set stroke color to white
        for (int i = 0; i < stars.length; i++) {
            // Map the audio amplitude to a speed value for the stars
            float speed = emil.map(emil.getSmoothedAmplitude(), 0, 1, 1, 5);
            stars[i] += speed; // Update the star position based on the speed
            if (stars[i] > emil.width) { // If the star goes off the screen
                stars[i] = emil.random(-emil.width, 0); // Reset its position to a random value on the left side of the
                                                        // screen
            }
            // Render the star as a point
            emil.point(stars[i], emil.random(-emil.height, emil.height));
        }

        emil.noFill(); // Set fill color to transparent
        emil.camera(0, -100, 200, 0, 0, 0, 5, 0, 0); // Set the camera position
        emil.translate(0, 0, 0); // Translate the scene
        emil.strokeWeight(4); // Set stroke weight

        // Animate and rotate the small sphere
        smallSphereSize = PApplet.map(PApplet.sin(PApplet.radians(smallSphereAngle)), -1, 1, 5, 10); // Map the sine of
                                                                                                     // the angle to a
                                                                                                     // size value for
                                                                                                     // the sphere
        smallSphereAngle -= 5; // Decrement the angle by 5 degrees

    }

}
