/**
 * The body class represents a single celestial object in the simulation.
 * This can be either the main star or some other type of random celestial body.
 * Each body tracks its position, velocity, size, mass, and display color and is capable
 * of updating their own positions based on velocity
 */

import java.awt.Color;

public class body {
    double x;
    double y;

    //velocity
    double vx;
    double vy;

    int size; //diameter in pixels
    double mass;
    Color color;

    /**
     * constructor of body class
     * @param x position on canvas
     * @param y position on canvas
     * @param vx velocity on x axis
     * @param vy velocity on y axis
     * @param size size of celestial body
     * @param mass mass of celestial body
     * @param color color of body on the canvas
     */
    public body(double x, double y, double vx, double vy, int size, double mass, Color color)
    {
        this.x=x;
        this.y=y;

        this.vx=vx;
        this.vy=vy;

        this.size=size;
        this.mass=mass;

        this.color=color;

    }

    /**
     * updates the celestial body's position based on velocity
     */
    public void update_position()
    {
        x+=vx;
        y+=vy;
    }
}