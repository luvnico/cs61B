public class Body {
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
}