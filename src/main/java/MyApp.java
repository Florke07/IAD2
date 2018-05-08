import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.utils.Debug;
import elements.Point;
import inputs.ReadData;
import inputs.ReadFromTXT;
import plotter.DrawPlot;
import renerator.Figure;
import renerator.GeneratePoints;

import javax.swing.*;
import java.util.ArrayList;

public class MyApp {
    public static void main(final String[] args) {
        System.out.println("Hello World!");
        ReadData reader = new ReadFromTXT();
        ArrayList<Double> lista;
        lista = reader.readDouble("TestData.txt");

        for (int i = 0;i<lista.size();i++) {
            System.out.println(lista.get(i));
        }


        double[][] points = new double[5][5];
        points[0][0] = 1; //x pierwszego punktu
        points[0][1] = 2; //y pierwszego punktu
        points[1][0] = 3; //x drugiego punktu
        points[1][1] = 4; //y drugiego punktu

        ArrayList<Point> pts = GeneratePoints.Generate(Figure.KWADRAT, 150);




    }
}
