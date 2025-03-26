/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pt_lab2;

/**
 *
 * @author Potek
 */
public class calculateThread extends Thread {
    private primeNumbers numbers;
    private resultCollect collector;
    private boolean running;
    
    calculateThread(primeNumbers numbers, resultCollect collector){
        this.numbers = numbers;
        this.collector = collector;
        running = true;
    }
    
    public void run() {
        while (running) {
            if (numbers.hasPendingNumbers()) {
                int number = numbers.getNextNumber();
                System.out.println(this.currentThread().getName() + " is checking " + number);
                
                if(isPrime(number)){
                    collector.getNumber(number);
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    private boolean isPrime(int number)
    {
        if(number <= 1)
        {
            return false;
        }
       for(int i = 2; i <= number/2; i++)
       {
           if((number%i) == 0){
               return  false;
           }
       }
       return true;
    }
    
    public void end(){
        running = false;
    }
}
