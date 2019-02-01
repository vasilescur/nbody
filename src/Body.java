/**
 * Represents a celestial body.
 *
 * @author Radu Vasilescu
 */
public class Body {
    private double myXPos;
    private double myYPos;
    private double myXVel;
    private double myYVel;
    private double myMass;

    private String myFileName;

    // Getters

    /**
     * Get the body's X position
     * 
     * @return x position
     */
    public double getX() {
        return this.myXPos;
    }

    /**
     * Get the body's Y position
     * 
     * @return y position
     */
    public double getY() {
        return this.myYPos;
    }

    /**
     * Get the body's X velocity
     * 
     * @return x velocity
     */
    public double getXVel() {
        return this.myXVel;
    }

    /**
     * Get the body's Y velocity
     * 
     * @return y velocity
     */
    public double getYVel() {
        return this.myYVel;
    }

    /**
     * Get the body's mass
     * 
     * @return mass
     */
    public double getMass() {
        return this.myMass;
    }

    /**
     * Get the body's file name
     * 
     * @return fileName
     */
    public String getName() {
        return this.myFileName;
    }

    /**
     * Create a body from parameters
     * 
     * @param xp       initial x position
     * @param yp       initial y position
     * @param xv       initial x velocity
     * @param yv       initial y velocity
     * @param mass     mass of object
     * @param filename filename of image for object animation<
     */
    public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
        this.myXPos = xp;
        this.myYPos = yp;
        this.myXVel = xv;
        this.myYVel = yv;
        this.myMass = mass;

        this.myFileName = filename;
    }

    /**
     * Copy constructor: copy instance variables from one body to this body
     *
     * @param b used to initialize this body
     */
    public Body(Body b) {
        this.myXPos = b.getX();
        this.myYPos = b.getY();
        this.myXVel = b.getXVel();
        this.myYVel = b.getYVel();
        this.myMass = b.getMass();
        this.myFileName = b.getName();
    }

    /**
     * Return the distance between this body and another
     * 
     * @param b the other body to which distance is calculated
     * @return distance between this body and b
     */
    public double calcDistance(Body b) {
        double dxSquared = Math.pow(b.getX() - this.getX(), 2);
        double dySquared = Math.pow(b.getY() - this.getY(), 2);

        return Math.sqrt(dxSquared + dySquared);
    }

    /**
     * Calculate the force exerted by another body on this body
     * 
     * @param p the other body
     * @return force
     */
    public double calcForceExertedBy(Body p) {
        // F = G * (m1 * m2)/ r^2

        double distance = this.calcDistance(p);
        double m1 = this.getMass();
        double m2 = p.getMass();
        double G = 6.67 * 1e-11;

        return (G * m1 * m2) / Math.pow(distance, 2);
    }

    /**
     * Calculate the force exerted by another body, along the X axis
     * @param p the other body
     * @return force
     */
    public double calcForceExertedByX(Body p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dx = p.getX() - this.getX();
        
        return F * (dx / r);
    }

    /**
     * Calculate the force exerted by another body, along the Y axis
     * @param p the other body
     * @return force
     */
    public double calcForceExertedByY(Body p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = p.getY() - this.getY();
        
        return F * (dy / r);
    }

    /**
     * Return the total/net force exerted on this body along the X axis by all
     * the bodies in the array parameter.
     * 
     * @param bodies all the bodies in the system
     * @return force
     */
    public double calcNetForceExertedByX(Body[] bodies) {
        double result = 0;

        for (Body b : bodies) {
            if (!b.equals(this)) {
                result += this.calcForceExertedByX(b);
            }
        }

        return result;
    }

    /**
     * Return the total/net force exerted on this body along the Y axis by all
     * the bodies in the array parameter.
     * 
     * @param bodies all the bodies in the system
     * @return force
     */
    public double calcNetForceExertedByY(Body[] bodies) {
        double result = 0;

        for (Body b : bodies) {
            if (!b.equals(this)) {
                result += this.calcForceExertedByY(b);
            }
        }

        return result;
    }

    /**
     * Updates the body's position and velocity according to a time step.
     * 
     * @param deltaT time step
     * @param xforce force along the X axis
     * @param yforce force along the Y axis
     */
    public void update(double deltaT, double xforce, double yforce) {
        double ax = xforce / this.getMass();
        double ay = yforce / this.getMass();

        double nvx = this.getXVel() + deltaT * ax;
        double nvy = this.getYVel() + deltaT * ay;

        double nx = this.getX() + deltaT * nvx;
        double ny = this.getY() + deltaT * nvy;

        this.myXPos = nx;
        this.myYPos = ny;

        this.myXVel = nvx;
        this.myYVel = nvy;
    }

    public void draw() {
        StdDraw.picture(this.getX(), this.getY(), "images/" + this.getName());
    }

}
