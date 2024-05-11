package Q1;

import java.util.Scanner;
import java.util.regex.Pattern;

class Person {
    private String firstName;
    private String lastName;
    private String PAN;
    private String addressPIN;

    public Person(String firstName, String lastName, String PAN, String addressPIN) throws InvalidPINException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.PAN = PAN;
        if (!isValidPIN(addressPIN)) {
            throw new InvalidPINException("OOPS!! YOU HAVE ENTERED THE WRONG PIN FOR ADDRESS ");
        }
        this.addressPIN = addressPIN;
    }

    private boolean isValidPIN(String pin) {

        return Pattern.matches("\\d{6}", pin);
    }
}

class Student extends Person {
    private String emailID;
    private String rollNumber;

    public Student(String firstName, String lastName, String PAN, String addressPIN, String emailID, String rollNumber)
            throws InvalidEmailException, InvalidRollNumberException, InvalidPINException {
        super(firstName, lastName, PAN, addressPIN);
        if (!isValidEmail(emailID)) {
            throw new InvalidEmailException("OOPS!! YOU HAVE ENTERED THE WRONG Email ADDRESS ");
        }
        if (!isValidRollNumber(rollNumber)) {
            throw new InvalidRollNumberException("OOPS!! YOU HAVE ENTERED THE WRONG ROLL NUMBER ");
        }
        this.emailID = emailID;
        this.rollNumber = rollNumber;
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("[A-Za-z0-9]*[!@#$&*]+[A-Za-z0-9]*@([A-Za-z0-9]*.[a-zA-Z]*)", email);
    }

    private boolean isValidRollNumber(String rollNumber) {
        return Pattern.matches("stud\\d{5}", rollNumber);
    }
}

class Employee extends Person {
    private String emailID;
    private String employeeID;

    public Employee(String firstName, String lastName, String PAN, String addressPIN, String emailID, String employeeID)
            throws InvalidEmailException, InvalidEmployeeIDException, InvalidPINException {
        super(firstName, lastName, PAN, addressPIN);
        if (!isValidEmail(emailID)) {
            throw new InvalidEmailException("OOPS!! YOU HAVE ENTERED THE WRONG Email ADDRESS ");
        }
        if (!isValidEmployeeID(employeeID)) {
            throw new InvalidEmployeeIDException("OOPS!! YOU HAVE ENTERED THE WRONG EMPLOYEE ID ");
        }
        this.emailID = emailID;
        this.employeeID = employeeID;
    }

    private boolean isValidEmployeeID(String employeeID) {
        return Pattern.matches("emp\\d{3}", employeeID);
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("[a-zA-Z0-9]*[!@#$&*]+[a-zA-Z0-9]*@([a-zA-Z]*[.]*[a-zA-Z]*[.]*[a-zA-Z]*)", email);
    }
}

class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

class InvalidPINException extends Exception {
    public InvalidPINException(String message) {
        super(message);
    }
}

class InvalidRollNumberException extends Exception {
    public InvalidRollNumberException(String message) {
        super(message);
    }
}

class InvalidEmployeeIDException extends Exception {
    public InvalidEmployeeIDException(String message) {
        super(message);
    }
}

public class ExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter Student Details:");
            System.out.print("First Name: ");
            String studentFirstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String studentLastName = scanner.nextLine();
            System.out.print("PAN: ");
            String studentPAN = scanner.nextLine();
            System.out.print("Address PIN: ");
            String studentAddressPIN = scanner.nextLine();
            System.out.print("Email ID: ");
            String studentEmailID = scanner.nextLine();
            System.out.print("Roll Number: ");
            String studentRollNumber = scanner.nextLine();

            Student student = new Student(studentFirstName, studentLastName, studentPAN, studentAddressPIN, studentEmailID, studentRollNumber);
            System.out.println("Student created successfully.");

            System.out.println("Enter Employee Details:");
            System.out.print("First Name: ");
            String employeeFirstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String employeeLastName = scanner.nextLine();
            System.out.print("PAN: ");
            String employeePAN = scanner.nextLine();
            System.out.print("Address PIN: ");
            String employeeAddressPIN = scanner.nextLine();
            System.out.print("Email ID: ");
            String employeeEmailID = scanner.nextLine();
            System.out.print("Employee ID: ");
            String employeeID = scanner.nextLine();

            Employee employee = new Employee(employeeFirstName, employeeLastName, employeePAN, employeeAddressPIN, employeeEmailID, employeeID);
            System.out.println("Employee created successfully.");


//            String nullStr = null;
//            System.out.println("Accessing a method on a null reference: " + nullStr.length());


            System.out.print("Enter the size of an array: ");
            int size = scanner.nextInt();
            int[] arr = new int[size];
            System.out.print("Enter the index to access (should be less than the array size): ");
            int index = scanner.nextInt();
            System.out.println("Accessing an element: " + arr[index]);

        } catch (InvalidEmailException | InvalidPINException | InvalidRollNumberException | InvalidEmployeeIDException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
