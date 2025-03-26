/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pt_lab2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Potek
 */
public class resultCollect {
    private Queue<Integer> numbers;
    private ReentrantLock lock;
    
    resultCollect(){
        numbers = new LinkedList<>();
        lock = new ReentrantLock();
    }
    
    public synchronized void getNumber(int number){
        numbers.add(number);
        notify();
    }
    
    public boolean isEmpty(){
        return numbers.isEmpty();
    }
    
    public synchronized int getResult() {
        return numbers.poll();
    }
}
