package renerator;

import elements.Point;

import java.util.ArrayList;
import java.util.Random;

public class GeneratePoints {

    public static ArrayList<Point> Generate(Figure fig, int count){
        Random rng = new Random();
        ArrayList<Point> points = new ArrayList<>();

        switch (fig){
            case KWADRAT:
                Point p1 = new Point(1,1);
                Point p2 = new Point(5,1);
                Point p3 = new Point(5,5);
                Point p4 = new Point(1,5);

                for (int i=0;i<count;i++){
                    points.add(new Point((rng.nextDouble()*4)+1, (rng.nextDouble()*4)+1));
                }
                break;
            case TROJKAT:
                Point t1 = new Point(2,0);
                Point t2 = new Point(-2,0);
                Point t3 = new Point(0,2);

                break;
            case OKRAG:
                int radius = 2;
                int iterator = 0;
                Point center = new Point(0,0);
                Point newPoint = new Point();
                double distance;

                while (iterator<count) {
                    newPoint = new Point((rng.nextDouble()*4)-2,(rng.nextDouble()*4)-2);
                    distance = dst(center, newPoint);

                    if (distance <= radius) {
                        points.add(newPoint);
                        iterator++;
                    }

                }
                break;
            default:
                break;
        }
        return points;
    }

    private static double dst(Point A, Point B) {
        double ans;
        ans = Math.sqrt((Math.pow((B.x-A.x),2))+(Math.pow((B.y-A.y),2)));
        return ans;
    }
}
