package renerator;

import elements.Point;

import java.util.ArrayList;
import java.util.Random;

public class Generate {

    public static ArrayList<Point> circled(int numbersOfCenters, int numbersOfPointsInFigure, int rangeMin, int rangeMax, int centresRadius) {
        ArrayList<Point> points = new ArrayList<>();
        Random rng = new Random();
        int delta = rangeMax - rangeMin;
        rangeMax -= 10;
        rangeMin += 10;

        for (int i=0;i<numbersOfCenters;i++) {

            int radius = centresRadius;
            int x = rng.nextInt((rangeMax - rangeMin)+1)+rangeMin;
            int y = rng.nextInt((rangeMax - rangeMin)+1)+rangeMin;
            //System.out.println("Radius: "+radius+" X: "+x+" Y: "+y);
            Point center = new Point(x,y);
            GeneratePoints gp = new GeneratePoints(Figure.OKRAG,numbersOfPointsInFigure,radius,center);
            ArrayList<Point> pts = new ArrayList<>();
            pts = gp.Generate();
            points.addAll(pts);
        }
        return points;
    }
}
