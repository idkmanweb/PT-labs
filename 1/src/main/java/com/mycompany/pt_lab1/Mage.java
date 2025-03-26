/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pt_lab1;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Potek
 */
public class Mage implements Comparable<Mage>,Comparator<Mage>{
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;
    
    public Mage(String name, int level, double power){
        this.name = name;
        this.level = level;
        this.power = power;
    }
    
    public void addApprentice(Mage apprentice){
        if(apprentices == null){
            apprentices = new HashSet<Mage>();
        }
        if(apprentices.contains(apprentice) == false){
            apprentices.add(apprentice);
        }
    }
    
    @Override
    public boolean equals(Object comparer){
        if(this == comparer) return true;
        if(comparer == null || getClass() != comparer.getClass()) return false;
        Mage o = (Mage) comparer;
        if(apprentices == null){
            if (o.apprentices != null) return false;
            return name.equals(o.name) && level == o.level && power == o.power;
        }
        return name.equals(o.name) && level == o.level && power == o.power && apprentices.equals(o.apprentices);
    }
    
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + level;
        long temp;
        temp = Double.doubleToLongBits(power);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        if(apprentices != null) result = prime * result + apprentices.hashCode();
        return result;
    }
    
    @Override
    public int compareTo(Mage mage){
        if (this.name.compareTo(mage.name) != 0) {
            return this.name.compareTo(mage.name);
        }
        if (this.level - mage.level != 0){
            return this.level - mage.level;
        }
        return (int)this.power - (int)mage.power;
    }
    
    @Override
    public String toString(){
        String result;
        result = "-Mage{name='" + name + "', level=" + level + ", power=" + power + "}\n";
        return result;
    }

    @Override
    public int compare(Mage o1, Mage o2) {
        if (o1.level - o2.level != 0){
            return o1.level - o2.level;
        }
        if (o1.name.compareTo(o2.name) != 0) {
            return o1.name.compareTo(o2.name);
        }
        return (int)o1.power - (int)o2.power;
    }
    
    public boolean checkIfApprentice(Set<Mage> setOfMages){
        for (Mage checking : setOfMages) {
            if(checking != this && checking.apprentices != null){
                if(checking.apprentices.contains(this)) return true;
            }
        }
        return false;
    }
    
    public void print(int level){
        for(int i = 0; i < level; i++){
            System.out.printf("-");
        }
        System.out.printf(this.toString());
        if(apprentices != null){
            for (Mage apprentice : apprentices) {
                apprentice.print(level+1);
            }
        }
    }
    
    public int numberOfApprentices(){
        int result = 0;
        if(this.apprentices == null) return 0;
        for(Mage apprentice : this.apprentices){
            result += 1 + apprentice.numberOfApprentices();
        }
        return result;
    }
}
