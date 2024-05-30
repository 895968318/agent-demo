package com.jd;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        Base base = new Base();
        while (true){
            base.process();
            Thread.sleep(3000);
        }
    }
}