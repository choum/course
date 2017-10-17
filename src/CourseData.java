//Heather Tran
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class CourseData { 
        ArrayList <String> classList = new ArrayList();
        ArrayList <String> studentList = new ArrayList(); 
        File temp1 = new File("temp.txt");
        File course = new File("course.txt");
        
    public ArrayList getCourseList() {
        //Read file and stores into classList and studentList
        try {   
            //Open File
            BufferedReader read = new BufferedReader(new FileReader(course));
            
            //First line from file is stored
            String line = read.readLine();
            
            //Declare array to hold parts of string
            String[] parts;
            
            //Loop to store courses into an ArrayList
            while (line != null) {
                parts = line.split("<>");
                if (!classList.contains(parts[0])) {
                    classList.add(parts[0]);
                }
                line = read.readLine();
            }
            read.close();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("This error is from getCourseList");
        }
        return classList;   
    }
    public ArrayList getStudentList(String s) {
        try {   
            //Open File
            BufferedReader read = new BufferedReader(new FileReader(course));
            
            //First line from file is stored
            String line = read.readLine();
            
            //Declare array to hold parts of string
            String[] parts;
            
            //Loop to store students into an ArrayList
            while (line != null) {
                parts = line.split("<>");
                if (s.contains(parts[0])) {
                    if (parts.length > 1) {
                        studentList.add(parts[1]);
                    }
                }
                line = read.readLine();
            }
            read.close();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("This error is from getStudentList");
        }
        return studentList;   
    }
    public void addStudent(String s, String t) {
        try {
            //Open File to read
            BufferedReader read = new BufferedReader(new FileReader(course));
            
            //First line from file is stored
            String line = read.readLine();
            
            //To rewrite file we will use a temporary file
            PrintWriter temp = new PrintWriter(new BufferedWriter(new FileWriter(temp1, false)));
            
            //Boolean variables for loop
            boolean check = false;
           
            while(!check){
                //Copys lines from old text file over to the temporary text file
                while (line != null) {
                    String ignore = s + "<>";    
                    if (!line.equalsIgnoreCase(ignore)) {
                            temp.println(line);
                    }
                    else {
                        check = true;
                    }
                    line = read.readLine();
                }
                //If a line in the old file is the same as ignore we will print it adding the student.
                if (check) {
                    temp.println(s + "<>" + t);
                }
                //If a course exists already and isn't in the format (EX: MHR 301<>) it will print on the old text file
                if (!check) {
                    temp.println(s + "<>" + t);
                    check = true;
                }
                
            }
            //Flush and close
            read.close();
            temp.flush();
            temp.close();
            //Rename file
            renameFile();
        } 
        catch (Exception e) {
           System.out.println(e);
           System.out.println("This error is from addStudent");
        }
    }
    public void addCourse (String s) {
        try {
            //Open File to read
            BufferedReader read = new BufferedReader(new FileReader(course));
            
            //First line from file is stored
            String line = read.readLine();
            
            //To write to the file we use PrintWriter
            PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(course,true)));
            boolean check = false;
            
            //Look for a null or "" line to write on where you will print the new course added.
            while (!check) {
                if (line == null || line.equals("")) {
                    p.println(s + "<>");
                    check = true;
                }
                line = read.readLine();
            }
            
            //Flush and close
            read.close();
            p.flush();
            p.close();
        } 
        catch (Exception e) {
           System.out.println(e);
           System.out.println("This error is from addCourse");
        }
    }
    public void deleteStudent (List <String> l, String t) {
        try {
            //Open File to read
            BufferedReader read = new BufferedReader(new FileReader(course));
            
            //First line from file is stored
            String line = read.readLine();
            
            //To write to the file we use PrintWriter
            PrintWriter temp = new PrintWriter(new BufferedWriter(new FileWriter(temp1, false)));
            
            //This ArrayList stores values to be deleted
            ArrayList <String> delete = new ArrayList();
            
            //Add values from List to ArrayList
            for (int i = 0; i < l.size(); i++) {
                delete.add(t + "<>" + l.get(i));
            }
            
            //Copy over to temp file lines that we don't want to delete
            while (line != null) {
                if (!delete.contains(line)) {
                    temp.println(line);
                }
                line = read.readLine();
            }
            
            //Flush and close
            read.close();
            temp.flush();
            temp.close();
            
            //Call renameFile method
            renameFile();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("This error is from deleteStudent");
        }
    }
    public void deleteCourse (String s) {
        try {
            //Open File to read
            BufferedReader read = new BufferedReader(new FileReader(course));
            
            //First line from file is stored
            String line = read.readLine();
            
            //To write to the file we use PrintWriter
            PrintWriter temp = new PrintWriter(new BufferedWriter(new FileWriter(temp1, false)));
            
            //Write lines to temporary file that don't contain the course name
            while (line != null) {
                if (!line.contains(s)) {
                    temp.println(line);
                }
                line = read.readLine();
            }
            //Flush and close
            read.close();
            temp.flush();
            temp.close();
            
            //Rename the file
            renameFile();
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("This error came from deleteCourse");
        }
    }
    public void renameFile() {
        //Delete the old file first
        course.delete();
        
        //Rename the file
        temp1.renameTo(course);
        
        //Delete the temporary file
        temp1.delete();
    }

}
