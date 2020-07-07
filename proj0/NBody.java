import java.io.FileInputStream;

/**
 * A class that will actually run your simulation
 * The goal of this class is to simulate a universe specified in one of the data files.
 */
public class NBody {
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
}