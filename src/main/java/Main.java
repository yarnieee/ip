import java.util.Random;

import fella.AbstractFella;
import fella.FartSmella;
import fella.SmartFella;

public class Main {
    private static AbstractFella<?> fella;

    private static void instantiateFella() {
        int whichFella = new Random().nextInt(2);

        switch (whichFella) {
            case 0:
                fella = new SmartFella();
                return;
            case 1:
                fella = new FartSmella();
                return;
        }

    }
    public static void main(String[] args) {
        instantiateFella();

        fella.run();
    }
}
