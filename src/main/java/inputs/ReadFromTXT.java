package inputs;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReadFromTXT implements ReadData{

    @Override
    public ArrayList<Integer> readInt(final String pathToFile) {
        ArrayList<Integer> list = new ArrayList<>();
        FileReader file;
        BufferedReader br;
        int readDone;

        try {
            file = new FileReader(pathToFile);
            br = new BufferedReader(file);

            while ((readDone = br.read()) != -1) {
                char rd = (char) readDone;
                readDone = Character.getNumericValue(rd);
                if (readDone != -1) {
                    list.add(readDone);
                    //System.out.println(readDone);
                }
            }
            br.close();
        } catch (FileNotFoundException fnfEX) {
            System.out.println("Unable to open file! File not found");
        } catch (IOException ioeEX) {
            System.out.println("readDone file error");
        }

        return list;
    }

    @Override
    public ArrayList<Double> readDouble(final String pathToFile) {
        ArrayList<Double> list = new ArrayList<>();
        Scanner scan;
        File file = new File(pathToFile);
        try {
            scan = new Scanner(file);
            scan.useDelimiter("[,\n]").useLocale(Locale.US);

            while(scan.hasNextDouble())
            {
                list.add( scan.nextDouble() );
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return list;
    }

    @Override
    public ArrayList<String> readStringLines(final String pathToFile) {
        ArrayList<String> list = new ArrayList<>();
        FileReader file;
        BufferedReader br;
        String readDone;

        try {
            file = new FileReader(pathToFile);
            br = new BufferedReader(file);

            while ((readDone = br.readLine()) != null) {
                //System.out.println(readDone);
                list.add(readDone);
            }
            br.close();
        } catch (FileNotFoundException fnfEX) {
            System.out.println("File not found");
        } catch (IOException ioEX) {
            System.out.println("Error reading file");
        }

        return list;
    }
}
