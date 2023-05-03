package c21716601;

import ie.tudublin.*; // Import classes from the ie.tudublin package
import ie.tudublin.Visual; // Import the Visual class from the ie.tudublin package

public class rockstar extends Visual {

    emil er; // Declare an "emil" object named "er"
    emil1 er1; // Declare an "emil1" object named "er1"
    bakht bakht; // Declare a "bakht" object named "bakht"
    nico nico; // Declare a "nico" object named "nico"
    us us; // Declare a "us" object named "us"
    us1 us1; // Declare a "us1" object named "us1"
    case4 case4; // Declare a "case4" object named "case4"
    visual6 visual6; // Declare a "visual6" object named "visual6"

    int v; // Declare an integer variable named "v"

    public void settings() {
        size(1000, 800, P3D); // Set the size of the window to 1000 x 800 pixels with 3D graphics
    }

    public void setup() {
        startMinim(); // Start the Minim audio library

        loadAudio("immortal.mp3"); // Load an audio file named "immortal.mp3"

        // Initialize the various objects
        er = new emil(this);
        er1 = new emil1(this);
        bakht = new bakht(this);
        nico = new nico(this);
        us = new us(this);
        us1 = new us1(this);
        case4 = new case4(this);
        visual6 = new visual6(this);

        colorMode(RGB); // Set the color mode to RGB
    }

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0); // Set the audio playback to the beginning of the track
            getAudioPlayer().play(); // Start playing the audio track
        }

        if (keyCode >= '0' && keyCode <= '5') {
            v = keyCode - '0'; // Set the value of "v" based on the key code
        }
    }

    public void draw() {
        background(0); // Set the background color to black

        try {
            // Call this if you want to use FFT data
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands();

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();

        // Use a switch statement to render the appropriate visual effect based on the
        // value of "v"
        switch (v) {
            case 1: {
                bakht.render(); // Render the "bakht" visual effect
                break;
            }

            case 2: {

                er.render(); // Render the "er" visual effect
                er1.render(); // Render the "er1" visual effect

                break;
            }

            case 3: {

                nico.render(); // Render the "nico" visual effect
                break;
            }

            case 4: {

                visual6.render(); // Render the "visual6" visual effect

                break;
            }

            case 5: {

                us1.render();// render the "us1" visual effect
                us.render();// render the "us" visual effect

                break;
            }

        }
    }

}
