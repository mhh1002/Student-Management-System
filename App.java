import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static ArrayList<User> userArrayList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Welcome to Student Management System");
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Please choose from the following:");
            System.out.println("1.Register  2. SignIn  3. Forget password  4. Exit");

            String choice = sc.next();

            switch (choice) {
                case "1":
                    register();
                    break;

                case "2":
                    signIn();
                    break;

                case "3":
                    forgetPassword();
                    break;

                case "4":
                    System.out.println("Thank you for using our System");
                    exit = true;
                    break;

                default:
                    System.out.println("Please choose a valid option");
                    break;
            }
        }
    }

    public static void register() {
        System.out.println("Welcome to Register! Please Enter your UserName");
        Scanner sc = new Scanner(System.in);

        // Username
        boolean exit1 = false;
        boolean flag1, flag2;
        String validUsername = null;
        while (!exit1) {
            String username = sc.next();
            flag1 = checkUserName(username); // check if username valid
            flag2 = usernameUnique(username, userArrayList); // check if id is unique

            // if both are true, exit while loop
            if (flag1 && flag2) {
                validUsername = username;
                exit1 = true;
            }
        }

        // Password
        System.out.println("Please Enter your password:");
        boolean exit2 = false;
        String validPwd = null;
        while (!exit2) {
            String pwd1 = sc.next();
            System.out.println("Please Enter your password again:");
            String pwd2 = sc.next();

            if (pwd1.equals(pwd2)) {
                validPwd = pwd2;
                exit2 = true;
            } else {
                System.out.println("Password does not match. Please Enter again");
            }
        }

        // PPSN
        boolean exit3 = false;
        String validPpsn = null;
        System.out.println("Please Enter your PPSN:");
        while (!exit3) {
            String ppsn = sc.next();
            exit3 = checkId(ppsn);
            if (exit3)
                validPpsn = ppsn;
        }

        // Phone number
        boolean exit4 = false;
        String validPhoneNum = null;
        System.out.println("Please Enter your phone number:");
        while (!exit4) {
            String phoneNum = sc.next();
            exit4 = checkPhoneNum(phoneNum);
            if (exit4)
                validPhoneNum = phoneNum;
        }
        User user = new User(validUsername, validPwd, validPpsn, validPhoneNum);
        userArrayList.add(user);
        System.out.println("You have successfully added a User!");
    }


    public static void signIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter your ID:");
        String username = sc.next();
        User user = determineUser(username);
        if (user == null) {
            System.out.println("User does not exist. Please Register first");
            return;
        } else {
            // Check if password is correct -- max 3 chances
            int count = 3;
            System.out.println("Please Enter password:");
            while (count > 0) {
                String pwd = sc.next();
                String correctPwd = user.getPassword();
                if (pwd.equals(correctPwd)) {
                    System.out.println("Password correct.");
                    break;
                } else {
                    count--;
                    System.out.println("password is incorrect.");
                    System.out.println("You have" + count + " times left");
                    System.out.println("Please Enter again:");
                }
            }
            // Used up all chances
            if (count <= 0) {
                System.out.println("You have used up all 3 chances.");
                System.out.println("Please contact admin to unlock account or try another user.");
                return;
            } else {
                // Checking verifying code and user input
                boolean exit = false;
                while (!exit) {
                    String code = generateCode();
                    System.out.println("Verifying code is :" + code);
                    System.out.println("Please Enter the verifying code:");
                    String inputCode = sc.next();
                    if (inputCode.equals(code)) {
                        System.out.println("Code is correct, you have successfully login!");
                        exit = true;
                        StudentManagementSystem sms = new StudentManagementSystem();
                        sms.startStudentSystem();
                    } else {
                        System.out.println("The code you Entered is invalid. Please try again:");
                    }
                }

        }

        }

    }

    public static void forgetPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter your username:");
        String username = sc.next();

        User u = determineUser(username);
        if (u == null) {
            System.out.println("username has not registered yet. Please register first.");
            return;
        } else {
            String userPpsn = u.getPpsn();
            String userPhoneNo = u.getPhoneNo();
            System.out.println("Pleases Enter you PPSN to reset your password:");
            String ppsn = sc.next();
            if (!ppsn.equals(userPpsn)) {
                System.out.println("The PPSN you entered is invalid.");
                return;
            } else {
                System.out.println("Pleases Enter you Phone Number to reset your password:");
                String phoneNo = sc.next();
                if (!phoneNo.equals(userPhoneNo)) {
                    System.out.println("The phone number you eneter is invalid.");
                    return;
                } else {
                    System.out.println("Please Enter the password you want to change:");
                    String changedPwd = sc.next();
                    System.out.println("Please Enter the password you want to change again:");
                    String changedPwd2 = sc.next();
                    if (changedPwd2.equals(changedPwd)) {
                        u.setPassword(changedPwd);
                        System.out.println("Your password has successfully changed");
                    } else {
                        System.out.println("The password you entered does not match. Please try agin.");
                    }
                }
            }


        }
    }

    public static boolean checkUserName(String username) {
        // Check for length of username
        int len = username.length();
        if (len < 3 || len > 15) {
            System.out.println("Username should be 3-15 characters");
            System.out.println("Please enter valid username again:");
            return false;
        }

        // Check for combination
        int alphabetNo = 0;
        for (int i = 0; i < len; i++) {
            char ch = username.charAt(i);

            if (!Character.isLetterOrDigit(ch)) {
                System.out.println("Username contains non-alphanumeric character");
                System.out.println("Please enter valid username again:");
                return false;
            } else if (Character.isLetter(ch)) {
                alphabetNo++;
            }
        }

        // Check for non-number only
        if (alphabetNo <= 0) {
            System.out.println("Username should also contains numeric character.");
            System.out.println("Please enter valid username again:");
            return false;
        }
        return true;
    }

    public static boolean usernameUnique(String username, ArrayList<User> userArrayList) {
        for (User u : userArrayList) {
            String s = u.getId();

            if (username.equals(s)) {
                System.out.println("Username already exists, please try another username:");
                return false;
            }
        }
        return true;
    }

    public static boolean checkId(String id) {
        // Check if length is 18
        if (id.length() != 18) {
            System.out.println("ID should contain exactly 18 characters");
            System.out.println("Please Enter a valid PPSN again:");
            return false;
        }

        // Check if start with '0'
        if (id.charAt(0) == '0') {
            System.out.println("First character of PPSN cannot be 0");
            System.out.println("Please Enter a valid PPSN again:");
            return false;
        }

        // Check if first 17 are num and 18th is num or 'x' or 'X'
        for (int i = 0; i < id.length(); i++) {
            char ch = id.charAt(i);
            if (i < 17) {
                if (!Character.isDigit(ch)) {
                    System.out.println("First 17 digits should all be a number.");
                    System.out.println("Please Enter a valid PPSN again:");
                    return false;
                }
            } else {
                if (!Character.isDigit(ch) && ch != 'x' && ch != 'X') {
                    System.out.println("The 18th digit should be either a number or 'x' or 'X");
                    System.out.println("Please Enter a valid PPSN again:");
                    return  false;
                }
            }
        }
        return true;
    }

    public static boolean checkPhoneNum(String number) {
        if (number.length() != 11) {
            System.out.println("Phone number should have exactly 11 digits.");
            System.out.println("Please try again:");
            return false;
        }

        if (number.charAt(0) == '0') {
            System.out.println("First digit of phone number cannot be 0.");
            System.out.println("Please try again:");
            return  false;
        }

        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (!Character.isDigit(ch)) {
                System.out.println("Phone number should not contain any non-numeric character");
                System.out.println("Please try again:");
                return false;
            }
        }
        return true;
    }

    // Generate a code for login
    public static String generateCode() {
        // Create an ArrayList with all letters
        ArrayList<Character> letterList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            letterList.add((char)('a' + i));
            letterList.add((char)('A' + i));
        }

        // Get 4 random letters
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(letterList.size());
            char ch = letterList.get(index);
            sb.append(ch);
        }

        // Generate a random number
        int randomNo = r.nextInt(10);
        sb.append(randomNo);

        // Generate Random order
        char[] Arr = sb.toString().toCharArray();
        int randomIndex = r.nextInt(Arr.length);
        char temp = Arr[randomIndex];
        Arr[randomIndex] = Arr[Arr.length - 1];
        Arr[Arr.length - 1] = temp;

        return new String(Arr);
    }

    // Determine if username exists
    public static User determineUser(String username) {
        for (User u : userArrayList) {
            String s = u.getId();
            if (s.equals(username)) {
                return u;
            }
        }
        return null;
    }
}
