package klasy;

import network.Network;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MojaKlasaTest {

    @org.junit.jupiter.api.Test
    void out(){
        ArrayList<Double> in = new ArrayList<>();
        in.add(0.2);
        in.add(0.2);
        Network net = new Network(2,10);
        System.out.println(net.toString());
        net.sort(in);
        System.out.println(net.toString());
    }
}