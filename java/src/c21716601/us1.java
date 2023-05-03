package c21716601;

import processing.core.PApplet;

public class us1 {
    
    rockstar emil; // Declaring an instance variable of class rockstar

    float smallSphereSize = 0; // Initializing the size of the small sphere
    float smallSphereAngle = 0; // Initializing the angle of rotation for the small sphere

    // Constructor of the class us1
    public us1(rockstar emil) {
        this.emil = emil; // Initializing the instance variable emil with the passed argument
    }

    // Method to render the graphics
    public void render() {
        // Setting the background color to transparent
        emil.noFill();
        // Positioning the camera to view the graphics
        emil.camera(0, 100, 200, 0, 0, 0, 1, 0, 0);
        emil.translate(0, 0, 0); // Translating to the origin
        emil.strokeWeight(4); // Setting the stroke weight

        float[] stars = new float[500]; // Declaring an array to store the positions of stars
        for (int i = 0; i < stars.length; i++) {
            stars[i] = emil.random(-emil.width, emil.width); // Setting the random x-position of stars
        }

        // Drawing the stars
        emil.stroke(250, 96, 78);
        for (int i = 0; i < stars.length; i++) {
            float speed = emil.map(emil.getSmoothedAmplitude(), 0, 1, 1, 5); // Setting the speed of movement of stars
            stars[i] += speed;
            if (stars[i] > emil.width) { // Resetting the position of the stars to the left of the screen
                stars[i] = emil.random(-emil.width, 0);
            }
            emil.point(stars[i], emil.random(-emil.height, emil.height)); // Drawing the stars
        }

        // Drawing the circle
        float[] b = emil.getSmoothedBands(); // Getting the smoothed frequency bands
        emil.stroke(250, 96, 78);
        float size = b[2];
        emil.circle(0, 0, size);

        emil.noFill();
        emil.camera(0, -100, 200, 0, 0, 0, 5, 0, 0);
        emil.translate(0, 0, 0);
        emil.strokeWeight(4);

        // Drawing another circle
        float[] b1 = emil.getSmoothedBands();
        emil.stroke(250, 96, 78);
        float size1 = b1[2];
        emil.circle(0, 0, size1);

        emil.noFill();
        emil.camera(0, 0, 200, 0, 0, 0, 5, 0, 0);
        emil.translate(0, 0, 0);
        emil.strokeWeight(2);

        // Animate and rotate the small sphere
        smallSphereSize = PApplet.map(PApplet.sin(PApplet.radians(smallSphereAngle)), -1, 1, 5, 10); // Setting the size
                                                                                                     // of the sphere
                                                                                                     // based on the
                                                                                                     // angle of
                                                                                                     // rotation
        smallSphereAngle -= 5; // Updating the angle of rotation for the next frame
    }

}
