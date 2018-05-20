package plotter;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;

public class DrawPlot {
    public static void draw (double[][] args1, double[][] args2,double[][] args3,double[][] args4, int EPOKI) {
        JavaPlot p = new JavaPlot();
        DataSetPlot s1 = new DataSetPlot(args1);
        p.addPlot(s1);
        DataSetPlot s2 = new DataSetPlot(args2);
        p.addPlot(s2);
        DataSetPlot s3 = new DataSetPlot(args3);
        p.addPlot(s3);
        DataSetPlot s4 = new DataSetPlot(args4);
        p.addPlot(s4);
        p.set("grid","");
        p.getAxis("x").setBoundaries(0,EPOKI);
        p.getAxis("y").setBoundaries(0,0.6);
        p.plot();
    }

    public static void draw (double[][] args1, double[][] args2) {
        JavaPlot p = new JavaPlot();
        DataSetPlot s1 = new DataSetPlot(args1);
        p.addPlot(s1);
        DataSetPlot s2 = new DataSetPlot(args2);
        p.addPlot(s2);
        p.plot();
    }


    public static void draw (double[][] args1, int EPOKI) {
        JavaPlot p = new JavaPlot();
        DataSetPlot s1 = new DataSetPlot(args1);
        p.addPlot(s1);
        p.set("grid","");
        p.getAxis("x").setBoundaries(-50,50);
        p.getAxis("y").setBoundaries(-50,50);
        p.plot();
    }
}
