public class Body {
    private static final double G = 6.67e-11; // scientific notation for gravitational constant G
    public double xxPos; // its current x position
    public double yyPos; // its current y position
    public double xxVel; // its current velocity in the x direction
    public double yyVel; // its current velocity in the y direction
    public double mass; // its mass
    public String imgFileName; // the name of the file that corresponds to the image that depicts the body(i.e. jupiter.gif)

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    
    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    /**
     * calculates the distance between two Bodys
     */
    public double calcDistance(Body other) {
        double sqrR = (this.xxPos - other.xxPos) * (this.xxPos - other.xxPos) 
            + (this.yyPos - other.yyPos) * (this.yyPos - other.yyPos);

        return Math.sqrt(sqrR);
    }

    /**
     * 
     * @param other
     * @return returns a double describing the force exerted on this body by the given body
     */
    public double calcForceExertedBy(Body other) {
        double r = calcDistance(other);

        return G * this.mass * other.mass / (r * r);
    }

    /**
     * describe the force exerted in the X and Y directions
     */
    public double calcForceExertedByX(Body other) {
        double netF = calcForceExertedBy(other);
        double dist = calcDistance(other);

        return netF * (other.xxPos - this.xxPos) / dist;
    }

    public double calcForceExertedByY(Body other) {
        double netF = calcForceExertedBy(other);
        double dist = calcDistance(other);

        return netF * (other.yyPos - this.yyPos) / dist;
    }

    /**
     * calculates the net X and net Y force exerted by all bodies in that array 
     * upon the current Body
     * @param bodys
     * @return
     */
    public double calcNetForceExertedByX(Body[] bodys) {
        double res = 0.0;
        for (Body body : bodys) {
            if (body.equals(this)) continue;
            res += calcForceExertedByX(body);
        }

        return res;
    }

    public double calcNetForceExertedByY(Body[] bodys) {
        double res = 0.0;
        for (Body body : bodys) {
            if (body.equals(this)) continue;
            res += calcForceExertedByY(body);
        }

        return res;
    }

    /**
     * determines how much the forces exerted on the body will cause that body to accelerate, 
     * and the resulting change in the body’s velocity and position 
     * in a small period of time dt
     * 
     * @param time
     * @param netForceX
     * @param netForceY
     */
    public void update(double time, double netForceX, double netForceY) {
        double accX = netForceX / this.mass;
        double accY = netForceY / this.mass;

        this.xxVel += time * accX;
        this.yyVel += time * accY;

        this.xxPos += time * xxVel;
        this.yyPos += time * yyVel;
    }
}