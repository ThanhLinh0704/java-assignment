
package managestudentsapplication;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    protected FileInputStream fis = null;
    protected ObjectInputStream ois = null;
    
    protected FileOutputStream fos = null;
    protected ObjectOutputStream oos = null;

    public void insert(Student student) {
//        try {
//            fos = new FileOutputStream("data\\" +student.getId() + ".txt");
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(student);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data\\" + student.getId() + ".txt"))) {
                writer.write("Name: " + student.getName() + "\t");
                writer.write("ID: " + student.getId() + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

    }
    
    
//    public Student read(Student student) {
//        try {
//            fis = new FileInputStream("data\\"+student.getId()+".txt");
//            ois = new ObjectInputStream(fis);
//            return (Student)ois.readObject();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public Student search(String id) {
//        File file = new File("data\\" + id + ".txt");
//        if (file.exists()) {
//            try (FileInputStream fis = new FileInputStream(file);
//                 ObjectInputStream ois = new ObjectInputStream(fis)) {
//                return (Student) ois.readObject();
//            } catch (IOException | ClassNotFoundException ex) {
//                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return null;
//    }
//    
//    public void edit(String id, Student updatedStudent) {
//    File file = new File("data\\" + id + ".txt");
//    if (file.exists()) {
//        file.delete();
//        insert(updatedStudent); 
//        System.out.println("Student information updated successfully.");
//    } else {
//        System.out.println("Student with ID " + id + " does not exist.");
//    }
//}
//
//    public void delete(String id) {
//    File file = new File("data\\" + id + ".txt");
//    if (file.exists()) {
//        file.delete();
//        System.out.println("Student with ID " + id + " has been deleted.");
//    } else {
//        System.out.println("Student with ID " + id + " does not exist.");
//    }
//    }
    
    public Student read(Student student) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data\\" + student.getId() + ".txt"))) {
            String name = reader.readLine();
            String id = reader.readLine();
            return new Student(name, id);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    public Student search(String id) {
        File file = new File("data\\" + id + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String name = reader.readLine();
                String studentId = reader.readLine();
                return new Student(name, studentId);
            } catch (IOException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public void edit(String id, Student updatedStudent) {
        File file = new File("data\\" + id + ".txt");
        if (file.exists()) {
        file.delete();
        insert(updatedStudent);
        updatedStudent.setId(id);
        System.out.println("Student information updated successfully.");
        } else {
        System.out.println("Student with ID " + id + " does not exist.");
        }
    }

    public void delete(String id) {
        File file = new File("data\\" + id + ".txt");
        if (file.exists()) {
            file.delete();
            System.out.println("Student with ID " + id + " had deleted.");
        } else {
            System.out.println("Student with ID " + id + " does not exist.");
        }
    }
    
    
//    public void displayAllStudents() {
//        File directory = new File("data");
//        File[] listFiles = directory.listFiles();
//        if (listFiles != null) {
//            for (File file : listFiles) {
//                if (file.isFile()) {
//                    try (FileInputStream fis = new FileInputStream(file);
//                         ObjectInputStream ois = new ObjectInputStream(fis)) {
//                        Student student = (Student) ois.readObject();
//                        System.out.println("Name: " + student.getName() + ", ID: " + student.getId());
//                    } catch (IOException | ClassNotFoundException ex) {
//                        Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        }
//    }
    
    public void displayAllStudents() {
        File directory = new File("data");
        File[] listFiles = directory.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
        
}





