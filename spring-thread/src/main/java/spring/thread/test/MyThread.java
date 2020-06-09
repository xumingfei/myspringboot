package spring.thread.test;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello,I am the defined thread created by extends Thread!");
    }

    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
    }
}
