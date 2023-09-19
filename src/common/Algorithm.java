/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.ArrayList;
import java.util.Collections;
import model.StudentData;
import view.Menu;

/**
 *
 * @author GoldCandy
 */
public class Algorithm {

    public void create(ArrayList<StudentData> data) {
        Input input = new Input();
        String iD;
        boolean check;
        do {
            iD = input.inputString("Enter ID");
            check = checkValidID(iD, data);
            if (check == false) {
                System.out.println("ID already exist!");
            }
        } while (check == false);
        String name = input.inputString("Enter Student Name");
        String semester = input.inputString("Enter Semester");
        int count = 0;

        do {
            StudentData dt = new StudentData();
            dt.setiD(iD);
            dt.setStudentName(name);
            dt.setSemester(semester);
            dt.setCourseName(input.inputStringMatch("Enter Course Name(1-Java|2-.Net|3-C/C++) or Enter 1 2 3 for quick add", "Java|\\.Net|C\\/C\\+\\+|[123]"));
            switch (dt.getCourseName()) {
                case "1" -> {
                    dt.setCourseName("Java");
                }
                case "2" -> {
                    dt.setCourseName(".Net");
                }
                case "3" -> {
                    dt.setCourseName("C/C++");
                }
            }
            data.add(dt);
            if (count == 0) {
                System.out.println("Student " + name + " is added!");
                count = 1;
            } else {
                System.out.println("Student " + name + " is updated");
            }
        } while (input.inputStringMatch("Student has more course?(Y/N)", "[YN]").matches("Y"));
    }

    public boolean checkValidID(String iD, ArrayList<StudentData> data) {
        for (StudentData item : data) {
            if (iD.matches(item.getiD())) {
                return false;
            }
        }
        return true;
    }

    public void sortDisplayByName(ArrayList<StudentData> data) {
        StudentComparator comparator = new StudentComparator();
        comparator.setCompareType(1);
        Collections.sort(data, comparator);
        int i = 0;
        for (StudentData item : data) {
            System.out.println(i + "-" + item.toString());
            i++;
        }
    }

    public boolean find(String str, String substr) {
        return str.toLowerCase().contains(substr.toLowerCase());
    }

    public void findSort(ArrayList<StudentData> data) {
        if (data.isEmpty()) {
            System.out.println("There is no student!");
            return;
        }
        Input input = new Input();
        ArrayList<StudentData> store = new ArrayList();
        sortDisplayByName(data);
        String name = input.inputString("Enter Student Name");
        for (StudentData item : data) {
            if (find(item.getStudentName(), name)) {
                store.add(item);
            }
        }
        if (store.isEmpty()) {
            System.out.println("Not found");
        } else {
            System.out.println("Student found: ");
            sortDisplayByName(store);
        }
    }

    public ArrayList<Integer> findAllLocation(ArrayList<StudentData> data) {
        ArrayList<Integer> location = new ArrayList();
        Input input = new Input();
        String iD = input.inputString("Enter Student ID");
        for (int i = 0; i < data.size(); i++) {
            if (find(data.get(i).getiD(), iD)) {
                location.add(i);
            }
        }
        return location;
    }

    public void updateDelete(ArrayList<StudentData> data) {
        if (data.isEmpty()) {
            System.out.println("There is no student!");
            return;
        }
        Input input = new Input();
        ArrayList<Integer> location = findAllLocation(data);
        if (location.isEmpty()) {
            System.out.println("ID not found!");
            return;
        }
        System.out.println("Student found: ");
        ArrayList<StudentData> store = new ArrayList();
        for (int i = 0; i < location.size(); i++) {
            store.add(data.get(location.get(i)));
        }
        sortDisplayByName(store);
        String opt = input.inputStringMatch("Do you want to update(U) or to delete(D) student", "[UD]");
        if (opt.equals("D")) {
            do {
                delete(data, location, store);
                System.out.println("DELETE COMPLETED!");
                if (store.isEmpty()) {
                    System.out.println("No more to delete!");
                    break;
                }
                opt = input.inputStringMatch("Continue to delete?(Y/N)", "[YN]");
            } while (opt.equals("Y"));
        } else {
            update(data, location, store);
        }
    }

    public void delete(ArrayList<StudentData> data, ArrayList<Integer> location, ArrayList<StudentData> store) {
        Input input = new Input();
        if (store.size() == 1) {
            sortDisplayByName(store);
            int l = location.get(0);
            data.remove(l);
            store.remove(0);
            return;
        }
        sortDisplayByName(store);
        int choice = input.getChoice(store);
        int l = location.get(choice);
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).equals(data.get(l))) {
                store.remove(i);
                break;
            }
        }
        data.remove(l);
    }

    public void update(ArrayList<StudentData> data, ArrayList<Integer> location, ArrayList<StudentData> store) {
        Input input = new Input();
        sortDisplayByName(store);
        String[] list = {"ID", "Name", "Semester", "Course", "Display", "Exit"};
        Menu menu = new Menu("STUDENT UPDATE", list) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 -> {
                        String s = input.inputString("Enter ID");
                        if (s.matches(data.get(location.get(0)).getiD())) {
                            System.out.println("That's the same ID!");
                        } else if (!checkValidID(s, data)) {
                            System.out.println("ID already exist");
                        } else {
                            for (int i = 0; i < location.size(); i++) {
                                data.get(location.get(i)).setiD(s);
                            }
                            System.out.println("UPDATED!");
                        }
                    }
                    case 2 -> {
                        String s = input.inputString("Enter Student Name");
                        if (s.matches(data.get(location.get(0)).getStudentName())) {
                            System.out.println("That's the same Name!");
                        } else {
                            for (int i = 0; i < location.size(); i++) {
                                data.get(location.get(i)).setStudentName(s);
                            }
                            System.out.println("UPDATED!");
                        }
                    }
                    case 3 -> {
                        String s = input.inputString("Enter Semester");
                        if (s.matches(data.get(location.get(0)).getSemester())) {
                            System.out.println("That's the same Semester!");
                        } else {
                            for (int i = 0; i < location.size(); i++) {
                                data.get(location.get(i)).setSemester(s);
                            }
                            System.out.println("UPDATED!");
                        }
                    }
                    case 4 -> {
                        sortDisplayByName(store);
                        if(store.size() == 1){
                            String s = input.inputStringMatch("Enter Course(1-Java|2-.Net|3-C/C++) or Enter 1 2 3 for quick add", "Java|\\.Net|C\\/C\\+\\+|[123]");
                            data.get(0).setCourseName(s);
                            System.out.println("UPDATED!");
                        }
                        int select = input.getChoice(store);
                        int l = location.get(select);
                        String s = input.inputStringMatch("Enter Course(1-Java|2-.Net|3-C/C++) or Enter 1 2 3 for quick add", "Java|\\.Net|C\\/C\\+\\+|[123]");
                        switch (s) {
                            case "1" -> {
                                s = "Java";
                            }
                            case "2" -> {
                                s = ".Net";
                            }
                            case "3" -> {
                                s = "C/C++";
                            }
                        }
                        if (s.matches(data.get(l).getCourseName())) {
                            System.out.println("That's the same Course!");
                        } else {
                            data.get(l).setCourseName(s);
                            System.out.println("UPDATED!");
                        }
                    }
                    case 5 -> {
                        for (int i = 0; i < location.size(); i++) {
                            System.out.println(data.get(location.get(i)));
                        }
                    }
                    case 6 -> {
                    }
                    default ->
                        System.out.println("No such choice!");
                }
            }
        };
        menu.run();
    }

    public void report(ArrayList<StudentData> data) {
        if (data.isEmpty()) {
            System.out.println("There is no student!");
            return;
        } else if (data.size() == 1) {
            System.out.println(displayReport(data.get(0), 1));
        }
        StudentComparator comparator = new StudentComparator();
        comparator.setCompareType(2);
        ArrayList<Integer> storeAmount = new ArrayList();
        ArrayList<Integer> location = new ArrayList();
        int count = 1;
        Collections.sort(data, comparator);
        for (int i = 1; i < data.size(); i++) {
            if (i == data.size() - 1) {
                if (data.get(i).getStudentName().equals(data.get(i - 1).getStudentName())) {
                    if (data.get(i).getCourseName().equals(data.get(i - 1).getCourseName())) {
                        count++;
                        location.add(i);
                        storeAmount.add(count);
                        break;
                    }
                } else {
                    location.add(i - 1);
                    storeAmount.add(count);
                    location.add(i);
                    storeAmount.add(1);
                    break;
                }
            }
            if (data.get(i).getStudentName().equals(data.get(i - 1).getStudentName())) {
                if (data.get(i).getCourseName().equals(data.get(i - 1).getCourseName())) {
                    count++;
                    continue;
                }
            }
            location.add(i - 1);
            storeAmount.add(count);
            count = 1;
        }
        for (int i = 0; i < location.size(); i++) {
            System.out.println(displayReport(data.get(location.get(i)), storeAmount.get(i)));
        }
    }

    public String displayReport(StudentData s, int amount) {
        return s.getStudentName() + " | " + s.getCourseName() + " | " + amount;
    }
}
