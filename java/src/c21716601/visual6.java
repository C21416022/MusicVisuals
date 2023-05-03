package c21716601;

import processing.core.PApplet;
import processing.core.PConstants;

public class visual6 {

    rockstar rs;
    private float[] hoopSizes = { 5.0f, 5.0f, 5.0f, 5.0f, 5.0f }; // starting size at 5 pixels for the 5 hoops
    private float[] hoopOpacities = { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f };
    private float[] newHoopSizes = { 5.0f, 5.0f, 5.0f, 5.0f, 5.0f }; // starting size at 5 pixels for the 5 new hoops
    private float[] newHoopOpacities = { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f };
    private int lastHoopTime = 0;
    private int hoopInterval = 1000; // 1 second interval
    private int currentHoop = 0;
    // private boolean[] shootingStarActive = new boolean[15];
    // private float[] shootingStarX = new float[15];
    // private float[] shootingStarY = new float[15];
    // private float[] shootingStarLength = new float[15];
    // private float[] shootingStarFade = new float[15];
    // private float[] shootingStarAngle = new float[15];
    float[] starSizes = new float[30];
    float[] starOpacities = new float[30];
    float[] starFades = new float[30];
    boolean[] starActive = new boolean[30];

    public visual6(rockstar rs) {
        this.rs = rs;
    }

    float angle = 0.0f;
    float width = 800f;
    float height = 800f;

    public void render() {

        rs.background(0);
        angle += 1.0; // Increment the angle by a small amount
        rs.camera(-1800, -1800, 0, 0, 0, 0, 1, 0, 0);

        rs.pushMatrix();
        rs.translate(400, 400);
        rs.rotateX(angle);
        float amplitude = rs.getSmoothedAmplitude();

        // Smoothly transition color values through RGB spectrum
        rs.colorMode(PConstants.RGB);
        rs.stroke((float) Math.abs(255 * Math.sin(angle)), (float) Math.abs(255 * Math.sin(angle + PConstants.PI / 2)),
                (float) Math.abs(255 * Math.sin(angle + PConstants.PI)));

        // Expand the current hoop
        hoopSizes[currentHoop] += amplitude * 20;
        hoopOpacities[currentHoop] = rs.map(amplitude, 0, 1, 0, 255);

        // Create and expand new hoops every 1 second interval
        int currentTime = rs.millis();
        if (currentTime - lastHoopTime > hoopInterval) {
            lastHoopTime = currentTime;
            currentHoop++;
            if (currentHoop >= hoopSizes.length) {
                currentHoop = 0;
            }
            newHoopSizes[currentHoop] = 5.0f;
            newHoopOpacities[currentHoop] = 0.0f;
        }

        // Expand the new hoops
        for (int i = 0; i < newHoopSizes.length; i++) {
            newHoopSizes[i] += amplitude * 20;
            newHoopOpacities[i] = rs.map(amplitude, 0, 1, 0, 255);
        }

        // Draw all the hoops
        for (int i = 0; i < hoopSizes.length; i++) {
            rs.strokeWeight(10);

            // Set stroke color to smoothly transition through RGB spectrum
            float r = (float) (255 * Math.abs(Math.sin(angle + i * PConstants.PI / 4)));
            float g = (float) (255 * Math.abs(Math.sin(angle + i * PConstants.PI / 4 + PConstants.PI / 2)));
            float b = (float) (255 * Math.abs(Math.sin(angle + i * PConstants.PI / 4 + PConstants.PI)));
            rs.colorMode(PConstants.RGB);
            rs.stroke(r, g, b, hoopOpacities[i]);

            rs.noFill();
            rs.ellipse(rs.width / 2, rs.height / 2, hoopSizes[i], hoopSizes[i]);

            // Set stroke color for new hoop
            r = (float) (255 * Math.abs(Math.sin(angle + i * PConstants.PI / 4)));
            g = (float) (255 * Math.abs(Math.sin(angle + i * PConstants.PI / 4 + PConstants.PI / 2)));
            b = (float) (255 * Math.abs(Math.sin(angle + i * PConstants.PI / 4 + PConstants.PI)));
            rs.colorMode(PConstants.RGB);
            rs.stroke(r, g, b, newHoopOpacities[i]);
            rs.noFill();

            // Reset the new hoop when it goes out of camera
            if (newHoopSizes[i] >= PApplet.sqrt(PApplet.pow(rs.width, 2) + PApplet.pow(rs.height, 2)) + 200) {
                newHoopSizes[i] = 5.0f;
                newHoopOpacities[i] = 0.0f;
            }
            rs.ellipse(rs.width / 2, rs.height / 2, newHoopSizes[i], newHoopSizes[i]);
        }
        rs.popMatrix();

        // rs.pushMatrix();
        // // Add shooting stars effect
        // for (int i = 0; i < starSizes.length; i++) {
        // if (!starActive[i]) {
        // // Generate a new star
        // starSizes[i] = rs.random(5, 15);
        // starOpacities[i] = 0;
        // starFades[i] = rs.random(100, 255);
        // starActive[i] = true;
        // }

        // // Update the star's opacity
        // float amplitude1 = rs.getSmoothedAmplitude();
        // float colorVal1 = rs.map(amplitude1, 0, 1, 0, 255);
        // starOpacities[i] = PApplet.lerp(starOpacities[i], colorVal1, 0.2f); //
        // Interpolate between the current
        // // opacity and the new opacity based on
        // // the audio amplitude

        // // Draw the star
        // rs.noStroke();
        // rs.fill(255, 255, 255, starOpacities[i]);
        // rs.ellipse(rs.random(0, rs.width), rs.random(0, rs.height), starSizes[i],
        // starSizes[i]);

        // // Fade out the star
        // if (starOpacities[i] >= starFades[i]) {
        // starOpacities[i] = starFades[i];
        // starActive[i] = false;
        // }
        // }

        // // Draw the shooting stars
        // rs.strokeWeight(10);
        // for (int j = 0; j < shootingStarActive.length; j++) {
        // if (shootingStarActive[j]) {
        // for (int i = 0; i < shootingStarLength[j]; i++) {
        // float fade = rs.map(i, 0, shootingStarLength[j], 255, 0);
        // rs.stroke(255, 255, 255, fade);
        // float x = shootingStarX[j] + i * PApplet.cos(shootingStarAngle[j]);
        // float y = shootingStarY[j] + i * PApplet.sin(shootingStarAngle[j]);
        // rs.line(x, y, x - PApplet.cos(shootingStarAngle[j] - PApplet.PI / 2),
        // y - PApplet.sin(shootingStarAngle[j] - PApplet.PI / 2));
        // }

        // // Fade out the shooting star
        // shootingStarFade[j] = 255 - (amplitude * 255);

        // if (shootingStarFade[j] <= 0) {
        // // Reset the shooting star
        // shootingStarActive[j] = false;
        // }
        // }
        // }
        // rs.popMatrix();
    }
}
