/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pt_lab2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Potek
 */
public class primeNumbers {
    private Queue<Integer> numbers;
    
    primeNumbers(){
        numbers = new LinkedList<>();
    }
    
    public synchronized void addNumber(int number){
        numbers.offer(number);
        notify();
    }
    
     public synchronized int getNextNumber() {
        while (numbers.isEmpty()) {
            try {
                wait(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        return numbers.poll();
    }
     
    public synchronized boolean hasPendingNumbers() {
        return !numbers.isEmpty();
    }
}
