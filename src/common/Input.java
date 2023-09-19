/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.ArrayList;
import java.util.Scanner;
import model.StudentData;

/**
 *
 * @author GoldCandy
 */
public class Input {

    public String inputString(String title) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        while (s.isBlank() || s.isEmpty()) {
            System.out.print(title + ": ");
            s = sc.nextLine();
        }
        return s;
    }

    public String inputStringMatch(String title, String regex) {
        Scanner sc = new Scanner(System.in);
        String s = null;
        while (s == null || !s.matches(regex)) {
            System.out.print(title + ": ");
            s = sc.nextLine();
        }
        return s;
    }

    public int getChoice(ArrayList<StudentData> data) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Enter Selection: ");
            choice = sc.nextInt();
        } while (choice < 0 || choice > data.size());
        return choice;
    }
}
