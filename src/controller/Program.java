/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Algorithm;
import common.Input;
import java.util.ArrayList;
import java.util.Collections;
import model.StudentData;
import view.Menu;

/**
 *
 * @author GoldCandy
 */
public class Program extends Menu {

    protected ArrayList<StudentData> data;
    protected static String[] mc = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};
    protected Algorithm algorithm;
    protected Input input;

    public Program() {
        super("WELCOME TO STUDENT MANAGEMENT", mc);
        algorithm = new Algorithm();
        input = new Input();
        data = new ArrayList();
    }
    
    public void initial(){
        int count = 0;
        String opt = "Y";
        System.out.println("You have to create atleast 10 student to continue...");
        while (count != 10 || opt.equals("Y")) {
            System.out.println("Student " + (count + 1) + ": ");
            algorithm.create(data);
            count++;
            if (count >= 10) {
                opt = input.inputStringMatch("Do you want to continue?(Y/N)", "[YN]");
            }
        }
    }
    
    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1: {
                System.out.println("CREATING SCREEN");
                algorithm.create(data);
                break;
            }
            case 2: {
                System.out.println("FIND/SORT SCREEN");
                algorithm.findSort(data);
                break;
            }
            case 3: {
                System.out.println("UPDATE/DELETE SCREEN");
                algorithm.updateDelete(data);
                break;
            }
            case 4: {
                System.out.println("REPORT SCREEN");
                algorithm.report(data);
                break;
            }
            case 5: {
                System.out.println("SYSTEM EXIT...");
                System.exit(0);
            }
            default: {
                System.out.println("No such choice!");
            }
        }
    }
}
