package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        House[] pqh = new House[1000];
        File file = new File("src/main/java/org/example/houses.txt");
        FileInputStream fis = new FileInputStream(file);
        Scanner scan = new Scanner(fis);
        for(int i = 0; i < pqh.length; i++) {
            String owner = scan.nextLine();
            int value = Integer.parseInt(scan.nextLine());
            pqh[i] = new House(owner, value);
        }
        scan.close();
        fis.close();
        heapsort(pqh);
        for(int i = 0; i < pqh.length; i++) {
            House temp = pqh[i];
            System.out.println((i+1) + ") Owner: " + temp.getOwner() + " Value: " + temp.getValue());
        }

        House ex1 = new House("Hoskey", 345678);
        House ex2 = new House("Hoskey2", 456789);
        House ex3 = new House("Hoskey3", 567890);
        PriorityQueueHeap pqhTest = new PriorityQueueHeap();
        pqhTest.add(ex1);
        pqhTest.add(ex2);
        pqhTest.add(ex3);
        PriorityQueueHeap pqhTest2 = new PriorityQueueHeap(pqhTest);
        PriorityQueueHeap pqhTest3 = pqhTest.deepCopy();

        System.out.println("\n Checking PriorityQueueHeap deepCopy() and Copy Constructor \nInitial PriorityQueueHeap Values: ");
        for(int i = 0; i < pqhTest.getLength(); i++) {
            House temp = new House(pqhTest.getMostExpensive());
            System.out.println("Owner: "  + temp.getOwner() + ", House Value:" + temp.getValue());
        }
        System.out.println("\nCopy Constructor PriorityQueueHeap Values: ");
        for(int i = 0; i < pqhTest2.getLength(); i++) {
            House temp = new House(pqhTest2.getMostExpensive());
            System.out.println("Owner: "  + temp.getOwner() + ", House Value:" + temp.getValue());
        }
        System.out.println("\ndeepCopy() method PriorityQueueHeap Values: ");
        for(int i = 0; i < pqhTest3.getLength(); i++) {
            House temp = new House(pqhTest3.getMostExpensive());
            System.out.println("Owner: "  + temp.getOwner() + ", House Value:" + temp.getValue());
        }
    }

    public static void heapsort(House[] a){
        PriorityQueueHeap pqh = new PriorityQueueHeap();
        for(int i = 0; i < a.length; i++){
            pqh.add(a[i]);
        }
        for(int i = 0; i < a.length; i++){
            a[i] = pqh.getMostExpensive();
        }
    }
}