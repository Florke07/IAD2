package renerator;

import elements.Point;

import java.util.ArrayList;
import java.util.Random;

public class GeneratePoints {

    private Figure figure;
    private int numbersOfPointsInFigure;


    public GeneratePoints(Figure figure, int numbersOfPointsInFigure) {
        this.figure = figure;
        this.numbersOfPointsInFigure = numbersOfPointsInFigure;
    }

    public ArrayList<Point> Generate(int radius, Point center) {
        Random rng = new Random();
        ArrayList<Point> points = new ArrayList<>();

        switch (figure) {
            case KWADRAT:
                Point p1 = new Point(1, 1);
                Point p2 = new Point(5, 1);
                Point p3 = new Point(5, 5);
                Point p4 = new Point(1, 5);

                for (int i = 0; i < numbersOfPointsInFigure; i++) {
                    points.add(new Point((rng.nextDouble() * 4) + 1, (rng.nextDouble() * 4) + 1));
                }
                break;
            case OKRAG:
                points = generateCircle(radius, center);
                break;
            default:
                break;
        }
        return points;
    }

    private ArrayList<Point> generateCircle(int radius, Point center) {
        Random rng = new Random();
        ArrayList<Point> points = new ArrayList<>();
        int iterator = 0;
        Point newPoint;
        double distance;

        while (iterator < numbersOfPointsInFigure) {
            newPoint = new Point((center.x - radius) + ((center.x + radius) - (center.x - radius)) * (rng.nextDouble()), (center.y - radius) + ((center.y + radius) - (center.y - radius)) * (rng.nextDouble()));
            distance = dst(center, newPoint);
            //System.out.println("Dystans= "+distance);
            //System.out.println("nowy punkt");
            if (distance <= radius) {
                points.add(newPoint);
                iterator++;
                //System.out.println("Puknt: "+iterator);
            }

        }
        return points;

    }

    private static double dst(Point A, Point B) {
        double ans;
        ans = Math.sqrt((Math.pow((B.x - A.x), 2)) + (Math.pow((B.y - A.y), 2)));
        return ans;
    }
}
