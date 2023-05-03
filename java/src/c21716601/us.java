package c21716601;

//import ie.tudublin.Visual;

public class us {

    rockstar us; // Declare a rockstar object

    // Declare a variable for rotating the spheres
    float rotation = 0;

    public us(rockstar us) // Constructor that takes a rockstar object as a parameter
    {
        this.us = us;
    }

    public void render() // Method for rendering the visual effect
    {
        // Generate an array of stars with random positions
        float[] stars = new float[500];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = us.random(-us.width, us.width);
        }

        // Draw stars that move based on audio amplitude
        us.stroke(4);
        for (int i = 0; i < stars.length; i++) {
            float speed = us.map(us.getSmoothedAmplitude(), 0, 1, 1, 5);
            stars[i] += speed;
            if (stars[i] > us.width) {
                stars[i] = us.random(-us.width, 0);
            }
            us.point(stars[i], us.random(-us.height, us.height));
        }

        // Draw two white spheres, one inside the other, that rotate around the y-axis
        us.stroke(140, 0, 0); // set stroke color to red
        rotation += us.getAmplitude() / 5f;
        us.rotateY(rotation);

        us.lights();
        us.strokeWeight((float) 0.5);
        us.pushMatrix();
        us.sphere(50); // draw a smaller white sphere
        us.sphere(100); // draw a larger white sphere
        us.popMatrix();

        // Calculate the position of the three circles around the center circle
        float angle = us.radians(us.frameCount * 2);
        float[] x = new float[4];
        float[] y = new float[4];
        for (int i = 0; i < 4; i++) {
            x[i] = 60 * us.cos(angle + i * us.radians(120));
            y[i] = 60 * us.sin(angle + i * us.radians(120));
        }

        // Draw the four circles, with the last one left empty
        for (int i = 0; i < 4; i++) {
            // Draw white sphere
            us.stroke(255);
            us.fill(255);
            us.pushMatrix();
            us.translate(x[i], y[i], 0);
            us.sphere(10);
            us.popMatrix();

            // Draw red sphere inside the white sphere
            us.stroke(255, 0, 0); // set stroke color to red
            us.fill(255, 0, 0); // set fill color to red
            us.pushMatrix();
            us.translate(x[i], y[i], 0);
            us.sphere(5);
            us.popMatrix();

            // Draw rotating red sphere in the center of the white sphere
            us.stroke(255, 0, 0); // set stroke color to red
            us.fill(255, 0, 0); // set fill color to red
            us.pushMatrix();
            us.translate(0, 0, -40); // move the sphere back in z direction
            us.rotateZ(us.radians(us.frameCount)); // rotate around z-axis based on frame count
            us.sphere(5);
            us.popMatrix();
        }
    }
}
