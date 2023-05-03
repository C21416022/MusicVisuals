package c21716601;

import processing.core.*;

public class emil {
    rockstar er;
    float rotation = 0;

    // Constructor that takes a "rockstar" object as a parameter
    public emil(rockstar er) {
        this.er = er;
    }

    // Method that renders the visual effect
    public void render() {
        // Set stroke color to transparent
        er.noFill();

        // Rotate the scene based on the audio amplitude
        rotation += er.getAmplitude() / 3f;
        er.rotateX(rotation);
        er.strokeWeight(2);

        // Set color mode to HSB
        er.colorMode(PConstants.HSB, 255);

        // Get an array of frequency bands from the audio input
        float[] b = er.getSmoothedBands();

        // Loop through the frequency bands and render a box for each one
        for (int i = 0; i < b.length; i++) {
            // Map the current frequency band index to a color value
            float colour = PApplet.map(i, 0, b.length, 0, 155);

            // Get the current audio amplitude
            float audioAmplitude = er.getAmplitude();

            // Map the audio amplitude to a hue value
            float hue = PApplet.map(audioAmplitude, 0, 1, 0, 255);

            // Set the stroke color to the calculated hue
            er.stroke(hue, 255, 255);

            // Render a box with the current color
            er.pushMatrix();
            er.box(colour);
            er.popMatrix();
        }

        // Reset color mode to RGB (optional)
        er.colorMode(PConstants.RGB, 255);
    }
}
