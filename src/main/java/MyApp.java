import inputs.ReadData;
import inputs.ReadFromTXT;

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
    }
}
