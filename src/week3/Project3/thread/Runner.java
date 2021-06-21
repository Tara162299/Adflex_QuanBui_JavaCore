package week3.Project3.thread;

public class Runner implements Runnable {
    public static void main(String[] args) {
        PositionHandler testPosition = new PositionHandler();
        Runner newTest = new Runner();
        Thread newShip = new Thread(newTest);

        for (String nameShip : testPosition.diffShipName()) {
            newShip.run();
            System.out.println(nameShip);
        }

    }

    @Override
    public void run() {
        Execution execution = new Execution("1234567");
        execution.process();
    }
}


