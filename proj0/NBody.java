import java.io.FileInputStream;

/**
 * A class that will actually run your simulation
 * The goal of this class is to simulate a universe specified in one of the data files.
 */
public class NBody {
    private static final String DATA_DIR = "data/";
    private static final String IMG_DIR = "images/";

    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readLine(); // skip line 1

        return in.readDouble();
    }

    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int size = in.readInt();
        in.readDouble(); // skip line 2
        Body[] bodies = new Body[size];

        int i = 0;
        while (i < size) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();

            bodies[i++] = new Body(xPos, yPos, xVel, yVel, mass, img);
        }

        return bodies;
    }

    private static void printFinalState(Body[] bodies, double radius) {
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
    }

    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = DATA_DIR + args[2];
        double universeRadius = readRadius(filename);
        // initializating canvas
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear();
        
        Body[] bodies = readBodies(filename);

        double time = 0.0;
        while (time < T) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            StdDraw.picture(0, 0, IMG_DIR + "starfield.jpg");

            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
                StdDraw.picture(bodies[i].xxPos, bodies[i].yyPos, IMG_DIR + bodies[i].imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        printFinalState(bodies, universeRadius);
    }
}