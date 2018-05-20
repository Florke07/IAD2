import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.utils.Debug;
import elements.Point;
import inputs.ReadData;
import inputs.ReadFromTXT;
import plotter.DrawPlot;
import renerator.Figure;
import renerator.Generate;
import renerator.GeneratePoints;

import javax.swing.*;
import java.util.ArrayList;

public class MyApp {
    public static void main(final String[] args) {
        System.out.println("Hello World!");
        ReadData reader = new ReadFromTXT();
        ArrayList<Double> lista;
        lista = reader.readDouble("TestData.txt");

        int ile = 10000;
        double[][] points = new double[5][5];
        points[0][0] = 1; //x pierwszego punktu
        points[0][1] = 2; //y pierwszego punktu
        points[1][0] = 3; //x drugiego punktu
        points[1][1] = 4; //y drugiego punktu


        ArrayList<Point> pts = new ArrayList<>();
        //Point c = new Point(2,3);
        //GeneratePoints gen = new GeneratePoints(Figure.OKRAG, ile, 5, c);

        pts = Generate.circled(5,500,-50,50, 5);
        ile = 5*500;
        double[][] ppp = new double[ile][ile];
        for (int i =0;i<ile;i++){
            ppp[i][0] = pts.get(i).x;
            ppp[i][1] = pts.get(i).y;
        }

        DrawPlot.draw(ppp,5);

    }
}
