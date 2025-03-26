/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pt_lab2;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Potek
 */
public class Pt_lab2 {

    public static void main(String[] args) throws InterruptedException {
        boolean running = true;
        int numberOfThreads;
        if(args.length != 0){
            numberOfThreads = Integer.parseInt(args[0]);
        } else {
            numberOfThreads = 3;
        }
        primeNumbers numbersQueue = new primeNumbers();
        resultCollect collector = new resultCollect();
        calculateThread[] threads = new calculateThread[numberOfThreads];
        
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new calculateThread(numbersQueue, collector);
            threads[i].start();
        }
        
        Thread resultPrinter = new Thread(() -> {
           while(true){
               synchronized(collector){
                   while(collector.isEmpty()){
                       try {
                           collector.wait();
                       } catch(InterruptedException e) {
                           break;
                       }
                   }
                   System.out.println(collector.getResult() + " is a prime number");
               }
           } 
        });
        resultPrinter.start();
        
        for(int i = 1; i <= 20; i++){
            numbersQueue.addNumber(i);
        }
        
        Scanner scan = new Scanner(System.in);
        int inputNumber;
        String input;
        
        
        while(true){
            input = scan.nextLine();
            if (input.equals("end")){
                System.out.println("Ending");
                
                try{
                for (Thread t : threads) {
                    t.join();
                }
                resultPrinter.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                resultPrinter.interrupt();
                for (calculateThread t : threads) {
                    t.end();
                }
                
                return;
            } else {
                inputNumber = Integer.parseInt(input);
                if(inputNumber > 0){
                    numbersQueue.addNumber(inputNumber);
                }
            }
        }
    }
}
