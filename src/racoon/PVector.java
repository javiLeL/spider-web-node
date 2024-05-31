package racoon;

import java.io.Serializable;

/**
 * This class make more easy work with vectors
 * 
 * @author JaviLeL
 * @version 1.6
 */
public class PVector implements Serializable {
    public Double x, y, z;

    /**
     * This constructor make a bidimensional Vector
     * 
     * @param x Set the parameter x of PVector
     * @param y Set the parameter y of PVector
     */
    public PVector(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This constructor make a bidimensional Vector
     * 
     * @param x Set the parameter x of PVector
     * @param y Set the parameter y of PVector
     */
    public PVector(Integer x, Integer y) {
        this.x = x + .0;
        this.y = y + .0;
    }

    /**
     * This constructor make a tridimensional Vector
     * 
     * @param x Set the parameter x of PVector
     * @param y Set the parameter y of PVector
     * @param z Set the parameter z of PVector
     */
    public PVector(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * This constructor make a tridimensional Vector
     * 
     * @param x Set the parameter x of PVector
     * @param y Set the parameter y of PVector
     * @param z Set the parameter z of PVector
     */
    public PVector(Integer x, Integer y, Integer z) {
        this.x = x + .0;
        this.y = y + .0;
        this.z = z + .0;
    }

    /**
     * This function can add other PVector to this PVector
     * 
     * @param add PVector how add to this PVector
     */
    public void add(PVector add) {
        x += add.x;
        y += add.y;
        /*
         * If you try to add a tridimensional PVector with bidimensonal PVector,
         * or vice versa you gona get a excption so you should be carefully
         */
        z = z != null && add.z != null ? z + add.z : null;
    }

    /**
     * This function can substract another PVector
     * 
     * @param sub
     */
    public void sub(PVector sub) {
        x -= sub.x;
        y -= sub.y;
        /*
         * If you try to sub a tridimensional PVector with bidimensonal PVector,
         * or vice versa you gona get a excption so you should be carefully
         */
        z = z != null && sub.z != null ? z - sub.z : null;
    }

    /**
     * This function normalize a PVector
     */
    public void normalize() {
        double module = this.module();
        x /= module;
        y /= module;
        z = z != null ? z / module : null;

    }

    /**
     * This function can set the limit of this Vector
     * TODO Make it to work with tridimensional vector
     * 
     * @param e Integer of max module can equals (or limit) of the vector
     */
    public void limit(double e) {
        double f = (double) (module() / e);
        if (f != 0) {
            x /= f;
            y /= f;
        }
    }

    /**
     * This funcion can make the module of Vector
     * TODO Make it to work with tridimensional vector
     * 
     * @return the integer of module of vector
     */
    public double module() {
        /*
         * To calculate the module of Vector we must add the x and y squared
         */
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double disct(PVector otherPVector) {
        PVector discPVector = new PVector(x - otherPVector.x, y - otherPVector.y);
        return discPVector.module();
    }

    public void multiply(double multiplier) {
        x *= multiplier;
        y *= multiplier;
        z = z != null ? z * multiplier : null;
    }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }
}