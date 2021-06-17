package week3.thread;

class ThreadTest implements Runnable {
    public void run() {
        System.out.println("thread is running...");
    }

    public static void main(String args[]) {
        System.out.println("System is on");
        ThreadTest m1 = new ThreadTest();
        Thread t1 = new Thread(m1);
        Thread t2 = new Thread(m1);
        t1.start();
        System.out.println("System is off");



        t2.start();

    }
}