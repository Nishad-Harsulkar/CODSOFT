import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and setters for student attributes
    // ...

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

public class StudentManagementSystem extends JFrame implements ActionListener {
    private ArrayList<Student> students;
    private JTextArea displayArea;
    private JButton addStudentButton, displayAllButton, exitButton;

    public StudentManagementSystem() {
        students = new ArrayList<>();

        displayArea = new JTextArea(15, 30);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane);

        addStudentButton = new JButton("Add Student");
        displayAllButton = new JButton("Display All Students");
        exitButton = new JButton("Exit");

        addStudentButton.addActionListener(this);
        displayAllButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(addStudentButton);
        add(displayAllButton);
        add(exitButton);

        setTitle("Student Management System");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudentButton) {
            String name = JOptionPane.showInputDialog(this, "Enter student name:");
            if (name != null && !name.isEmpty()) {
                String rollNumberStr = JOptionPane.showInputDialog(this, "Enter student roll number:");
                if (rollNumberStr != null && !rollNumberStr.isEmpty()) {
                    try {
                        int rollNumber = Integer.parseInt(rollNumberStr);
                        String grade = JOptionPane.showInputDialog(this, "Enter student grade:");
                        if (grade != null && !grade.isEmpty()) {
                            Student student = new Student(name, rollNumber, grade);
                            students.add(student);
                            saveDataToFile();
                            JOptionPane.showMessageDialog(this, "Student added successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Grade cannot be empty.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid roll number format.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Roll number cannot be empty.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.");
            }
        } else if (e.getSource() == displayAllButton) {
            displayAllStudents();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void displayAllStudents() {
        displayArea.setText("");
        for (Student student : students) {
            displayArea.append(student.toString() + "\n");
        }
    }

    private void saveDataToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("students.txt"))) {
            outputStream.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}
