public class Main {
    private AbstractFella fella;

    private void instantiateFella() {
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
    public void main() {
        instantiateFella();

        fella.run();
    }
}
