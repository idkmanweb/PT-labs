/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pt_lab1;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Potek
 */
public class Pt_lab1 {

    public static void main(String[] args) {
        Set<Mage> mages = null;
        
        if(args.length == 0){
            mages = new HashSet<Mage>();
        }
        else if(args[0] == "naturalny"){
            mages = new TreeSet<Mage>();
        }
        else if(args[0] == "alternatywny"){
            Comparator<Mage> comp = (Mage o1, Mage o2) -> (o1.compare(o1, o2));
            mages = new TreeSet<Mage>(comp);
        }
        else {
            System.out.println("nieprawidlowy argument");
            System.exit(1);
        }
        
        Mage mage1 = new Mage("Mage 1", 10, 10.0);
        Mage mage2 = new Mage("Mage 2", 20, 43.0);
        Mage mage3 = new Mage("Mage 3", 6, 4.0);
        Mage mage4 = new Mage("Mage 4", 11, 23.0);
        Mage mage5 = new Mage("Mage 5", 9, 0.7);
        Mage mage6 = new Mage("Mage 6", 1, 1.3);
        Mage mage7 = new Mage("Mage 7", 2, 13.0);
        Mage mage8 = new Mage("Mage 8", 15, 43.0);
        Mage mage9 = new Mage("Mage 9", 3, 3.5);
        Mage mage10 = new Mage("Mage 10", 14, 32.0);
        
        mage5.addApprentice(mage6);
        mage1.addApprentice(mage5);
        mage1.addApprentice(mage7);
        mage4.addApprentice(mage8);
        mage10.addApprentice(mage9);
        
        mages.add(mage1);
        mages.add(mage2);
        mages.add(mage3);
        mages.add(mage4);
        mages.add(mage5);
        mages.add(mage6);
        mages.add(mage7);
        mages.add(mage8);
        mages.add(mage9);
        mages.add(mage10);
        
        for (Mage current : mages) {
            if(!current.checkIfApprentice(mages)){
                current.print(0);
            }
        }
        
        int stats[] = new int[mages.size()];
        int index = 0;
        for (Mage current : mages) {
            stats[index] = current.numberOfApprentices();
            index++;
        }
        for (int current : stats) {
            System.out.printf(current + ", ");
        }
    }
}
