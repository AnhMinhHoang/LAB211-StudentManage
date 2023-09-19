/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.Comparator;
import model.StudentData;

/**
 *
 * @author GoldCandy
 */
public class StudentComparator implements Comparator<StudentData> {

    private int compareType;

    public int getCompareType() {
        return compareType;
    }

    public void setCompareType(int compareType) {
        this.compareType = compareType;
    }

    @Override
    public int compare(StudentData o1, StudentData o2) {
        if (compareType == 1) {
            return o1.getStudentName().compareTo(o2.getStudentName());
        } else {
            int nameCompare = o1.getStudentName().compareTo(o2.getStudentName());
            if (nameCompare != 0) {
                return nameCompare;
            } else {
                return o1.getCourseName().compareTo(o2.getCourseName());
            }
        }
    }

}
