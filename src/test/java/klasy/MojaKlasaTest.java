package klasy;

import network.NetworkGN;

import java.util.ArrayList;

class MojaKlasaTest {

    @org.junit.jupiter.api.Test
    void out(){
        ArrayList<Double> in = new ArrayList<>();
        in.add(0.2);
        in.add(0.2);
        NetworkGN net = new NetworkGN(2,10);
        System.out.println(net.toString());
        net.sort(in);
        System.out.println(net.toString());
    }
}