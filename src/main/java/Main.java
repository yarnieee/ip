import fella.AbstractFella;
import fella.FartSmella;
import fella.SmartFella;

public class Main {
    private static AbstractFella fella;

    private static void instantiateFella() {
        int whichFella = (int)(Math.random() * 101) % 2;

        switch (whichFella) {
            case 0:
                fella = new SmartFella();
                break;
            case 1:
                fella = new FartSmella();
                break;
        }

    }
    public static void main(String[] args) {
        instantiateFella();

        fella.run();
    }
}
