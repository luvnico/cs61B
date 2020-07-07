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

    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = DATA_DIR + args[2];
        double universeRadius = readRadius(filename);
        // initializating canvas
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear();
        StdDraw.picture(0, 0, IMG_DIR + "starfield.jpg");
        Body[] bodies = readBodies(filename);
        for (Body body : bodies) {
            StdDraw.picture(body.xxPos, body.yyPos, IMG_DIR + body.imgFileName);
        }
        StdDraw.show();
    }
}