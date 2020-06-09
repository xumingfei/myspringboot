package spring.thread.test;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello ,I am the defined thread created by implements Runnable;");
    }

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
