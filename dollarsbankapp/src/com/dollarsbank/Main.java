//package com.example.DollarsBankCoreJavaAppVl;
//
//import com.example.DollarsBankCoreJavaAppVl.controller.StudentController;
//import com.example.DollarsBankCoreJavaAppVl.model.Student;
//import com.example.DollarsBankCoreJavaAppVl.utility.StudentView;
//
//public class Main {
//    public static void main(String[] args) {
//        //fetch student record based on his roll no from the database
//        Student model  = retriveStudentFromDatabase();
//        //Create a view : to write student details on console
//        StudentView view = new StudentView();
//        StudentController controller = new StudentController(model, view);
//        controller.updateView();
//        //update model data
//        controller.setStudentName("John");
//        controller.updateView();
//    }
//    private static Student retriveStudentFromDatabase(){
//        Student student = new Student();
//        student.setName("Robert");
//        student.setRollNo("10");
//        return student;
//    }
//}
