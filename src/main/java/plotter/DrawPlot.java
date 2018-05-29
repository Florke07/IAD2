package plotter;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.ImageTerminal;

import javax.imageio.ImageIO;
import java.io.*;

public class DrawPlot {
    /*public static void draw(double[][] args1, double[][] args2, double[][] args3, double[][] args4, int EPOKI) {
        JavaPlot p = new JavaPlot();
        DataSetPlot s1 = new DataSetPlot(args1);
        p.addPlot(s1);
        DataSetPlot s2 = new DataSetPlot(args2);
        p.addPlot(s2);
        DataSetPlot s3 = new DataSetPlot(args3);
        p.addPlot(s3);
        DataSetPlot s4 = new DataSetPlot(args4);
        p.addPlot(s4);
        p.set("grid", "");
        p.getAxis("x").setBoundaries(0, EPOKI);
        p.getAxis("y").setBoundaries(0, 0.6);
        p.plot();
    }*/

    public static void draw(double[][] args1, double[][] args2, String path) {
        ImageTerminal png = new ImageTerminal();
        JavaPlot p = new JavaPlot();
        File file = new File(path);
        try {
            file.createNewFile();
            png.processOutput(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        p.setTerminal(png);
        PlotStyle style = new PlotStyle(Style.POINTS);
        style.setPointSize(1);
        style.setPointType(13);
        PlotStyle style2 = new PlotStyle(Style.POINTS);
        style2.setPointType(5);
        style2.setPointSize(1);
        DataSetPlot s1 = new DataSetPlot(args1);
        s1.setPlotStyle(style);
        p.addPlot(s1);
        DataSetPlot s2 = new DataSetPlot(args2);
        s2.setPlotStyle(style2);
        p.addPlot(s2);
        p.getAxis("x").setBoundaries(-15, 15);
        p.getAxis("y").setBoundaries(-15, 15);

        p.plot();
        try {
            ImageIO.write(png.getImage(), "png", file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

/*
    public static void draw(double[][] args1, int EPOKI) {
        JavaPlot p = new JavaPlot();
        DataSetPlot s1 = new DataSetPlot(args1);
        p.addPlot(s1);
        p.set("grid", "");
        p.getAxis("x").setBoundaries(-50, 50);
        p.getAxis("y").setBoundaries(-50, 50);
        p.plot();
    }*/

    public static void draw(double[][] args1, double[][] args2, double[][] args3, String path) {
        ImageTerminal png = new ImageTerminal();
        JavaPlot p = new JavaPlot();
        File file = new File(path);
        try {
            file.createNewFile();
            png.processOutput(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        p.setTerminal(png);
        PlotStyle style = new PlotStyle(Style.POINTS);
        style.setPointSize(1);
        style.setPointType(13);
        PlotStyle style2 = new PlotStyle(Style.POINTS);
        style2.setPointType(5);
        style2.setPointSize(1);
        PlotStyle style3 = new PlotStyle(Style.POINTS);
        style3.setPointSize(2);
        style3.setPointType(7);
        DataSetPlot s1 = new DataSetPlot(args1);
        s1.setPlotStyle(style);
        p.addPlot(s1);
        DataSetPlot s2 = new DataSetPlot(args2);
        s2.setPlotStyle(style2);
        p.addPlot(s2);
        DataSetPlot s3 = new DataSetPlot(args3);
        s3.setPlotStyle(style3);
        p.addPlot(s3);
        p.getAxis("x").setBoundaries(-15, 15);
        p.getAxis("y").setBoundaries(-15, 15);
        p.plot();
        try {
            ImageIO.write(png.getImage(), "png", file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void draw(double[][] args1, double[][] args2, double[][] args3) {
        JavaPlot p = new JavaPlot();
        PlotStyle style = new PlotStyle(Style.POINTS);
        style.setPointSize(1);
        style.setPointType(13);
        PlotStyle style2 = new PlotStyle(Style.POINTS);
        style2.setPointType(5);
        style2.setPointSize(1);
        PlotStyle style3 = new PlotStyle(Style.POINTS);
        style3.setPointSize(2);
        style3.setPointType(7);
        DataSetPlot s1 = new DataSetPlot(args1);
        s1.setPlotStyle(style);
        p.addPlot(s1);
        DataSetPlot s2 = new DataSetPlot(args2);
        s2.setPlotStyle(style2);
        p.addPlot(s2);
        DataSetPlot s3 = new DataSetPlot(args3);
        s3.setPlotStyle(style3);
        p.addPlot(s3);
        p.getAxis("x").setBoundaries(-15, 15);
        p.getAxis("y").setBoundaries(-15, 15);
        p.plot();
    }
}
