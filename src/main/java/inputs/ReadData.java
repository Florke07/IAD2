package inputs;

import java.util.ArrayList;

public interface ReadData {
    ArrayList<Integer> readInt(String pathToFile);

    ArrayList<Double> readDouble(final String pathToFile);

    ArrayList<String> readStringLines(final String pathToFile);
}
