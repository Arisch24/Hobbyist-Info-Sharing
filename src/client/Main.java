package client;

import java.util.Scanner;
import adt.*;
import entity.*;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Iterator;

// all exceptions
import java.io.IOException;
import java.time.DateTimeException;
import java.util.InputMismatchException;


public class Main {
    static Scanner scan = new Scanner(System.in);
    // all the necessary ADTs
    static ArrayStack<Sharing> page = new ArrayStack<Sharing>();
    static ArrayList<Member> memberList = new ArrayList<Member>();
    // to store the member's hobbies
    static LinkedList<Hobby> hobbies;
    // to store the current member
    static Member member = new Member();
    // to search the page
    static Sharing sharing = new Sharing();
    // input for user input
    static int input;
    
    
    public static int UserInput() {
        while (true) {
            try {
                input = scan.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Enter a number pls.");
                scan.next();
            }
        }
        return input;
    }
    
    public static void Start() {
        System.out.println("=======================================");
        System.out.println("Hobbyist Info Sharing System");
        System.out.println("=======================================");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 3);
        
            switch(choice){
                case 1:
                    Register();
                    break;
                case 2:
                    Login();
                    break;
                case 3:
                    Exit();
                    break;
            }
    }
    
    public static void Register() {
        System.out.println("=======================================");
        System.out.println("Registration");
        System.out.println("=======================================");
        
        // get members name
        System.out.print("Enter your name: ");
        scan.nextLine();
        String name = scan.nextLine();
        // get password
        System.out.print("Enter your password: ");
        String password = scan.nextLine();
        
        // add member to memberlist
        Member newMember = new Member(name, password);
        memberList.add(newMember);
        Start();
    }
    
    public static void Login() {
        scan = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.println("Login");
        System.out.println("=======================================");
        System.out.println("1. Continue");
        System.out.println("2. Back");
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 2);
        
        switch(choice){
            case 1:
                break;
            case 2:
                Start();
                break;
        }
        // get user's name and password to verify
        System.out.print("Enter your name: ");
        scan.nextLine();
        String name = scan.next();
        
        System.out.print("Enter your password: ");
        scan.nextLine();
        String password = scan.nextLine();
        
        // verify user here
        if(VerifyUser(name.trim(), password.trim())){
            System.out.println("You are logged in!");
            Navigation();
        }else {
            System.out.println("You are not registered. Try again.");
            Login();
        }
    }
    
    public static boolean VerifyUser(String name, String password) {
        // iterator for the ADTs
        Iterator<Member> memberListIterator = memberList.getIterator();
        boolean verify = false;
        
        while(memberListIterator.hasNext()){
            member = memberListIterator.next();
            
            if(member.getName().trim().equals(name.trim()) && member.getPassword().trim().equals(password.trim())){
                verify = true;
                hobbies = member.getHobbyList();
                break;
            }
        }
        // at the end of the loop the member's details get stored in the global scope member object (line 132)
        // and the member's hobbies get stored in the linked list (line 136)
        return verify;
    }
    
    public static void Navigation() {
        // member location in the list
        int location = memberList.indexOf(member);
        
        System.out.println("=======================================");
        System.out.println("Navigation");
        System.out.println("=======================================");
        System.out.println("1. Hobbies section");
        System.out.println("2. View Profile");
        System.out.println("3. Update Profile");
        System.out.println("4. Delete Profile");
        System.out.println("5. Logout");
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 5);
        
        switch(choice){
            case 1:
                Hobbies(location);
                break;
            case 2:
                ViewProfile();
                break;
            case 3:
                UpdateProfile(location);
                break;
            case 4:
                DeleteProfile(location);
                break;
            case 5:
                Login();
                break;
        }
    }
    
    public static void ViewProfile() {
        System.out.println("=======================================");
        System.out.println("View Profile");
        System.out.println("=======================================");
        System.out.println("Name: " + member.getName());
        System.out.println("Password: " + member.getPassword());
        System.out.println("=======================================");
        System.out.println("Press any key to continue...");
        scan.next();
        System.out.println();
        Navigation();
    }
    
    public static void UpdateProfile(int location) {
        
        System.out.println("=======================================");
        System.out.println("Update Profile");
        System.out.println("=======================================");
        System.out.println("Name: " + member.getName());
        System.out.println("Password: " + member.getPassword());
        System.out.println("=======================================");
        System.out.println("1. Change name.");
        System.out.println("2. Change password.");
        System.out.println("3. Back");
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 3);
        
        scan.nextLine();
        switch(choice){
            case 1:
                System.out.println("Enter your new name: ");
                String newName = scan.nextLine();
                // replace the member list with updated member name
                member.setName(newName);
                memberList.replace(location, member);
                break;
            case 2:
                System.out.println("Enter your new password: ");
                String newPassword = scan.nextLine();
                // replace the member list with updated member password
                member.setPassword(newPassword);
                memberList.replace(location, member);
                break;
            case 3:
                Navigation();
                break;
        }
        Navigation();
    }
    
    public static void DeleteProfile(int location) {
        
        System.out.println("Confirm deletion of profile? (Y or N) (Note: This action cannot be undone) ");
        char selection;
        do {
            selection = scan.next().charAt(0);
            // do while the selection is not equal to either of these characters
        }while(selection != 'y' && selection != 'Y' && selection != 'n' && selection != 'N');
        
        switch(selection){
            case 'y':
                Delete(location);
                Start();
                break;
            case 'Y':
                Delete(location);
                Start();
                break;
            case 'n':
                Navigation();
                break;
            case 'N':
                Navigation();
                break;
        }
    }
    
    public static void Delete(int location) {
        // delete the user profile
        memberList.remove(location);
        System.out.println("Deletion successful.");
    }
    
    public static void Hobbies(int location) {
        System.out.println("=======================================");
        System.out.println("Hobbies Section");
        System.out.println("=======================================");
        System.out.println("1. Add a hobby");
        System.out.println("2. View Hobbies");
        System.out.println("3. Update a hobby");
        System.out.println("4. Delete a hobby");
        System.out.println("5. Go to hobby sharing page");
        System.out.println("6. Back");
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 6);
        
        switch(choice) {
            case 1:
                AddHobby(location);
                break;
            case 2:
                ViewHobbies();
                break;
            case 3:
                UpdateHobby(location);
                break;
            case 4:
                DeleteHobby(location);
                break;
            case 5:
                ViewSharingPage(location);
                break;
            case 6:
                Navigation();
                break;
        }
    }
    
    public static void AddHobby(int location) {
        System.out.println("=======================================");
        System.out.println("Add a hobby");
        System.out.println("=======================================");
        System.out.println("1. Continue");
        System.out.println("2. Back");
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 2);
        
        switch(choice) {
            case 1:
                System.out.print("Enter new hobby name:");
                scan.nextLine();
                String newHobbyName = scan.nextLine();
                System.out.println(newHobbyName);
                hobbies.add(new Hobby(newHobbyName));
                member.setHobbyList(hobbies);
                memberList.replace(location, member);
                break;
            case 2:
                // automatically returns to hobby function
                break;
        }
        Hobbies(location);
    }
    
    public static void ViewHobbies() {
        int location = memberList.indexOf(member);
        System.out.println("=======================================");
        System.out.println("View Hobbies");
        System.out.println("=======================================");
        System.out.println(hobbies);
        System.out.println("Press any key to continue...");
        scan.next();
        Hobbies(location);
    }
    
    public static void UpdateHobby(int location) {
        System.out.println("=======================================");
        System.out.println("Update a hobby");
        System.out.println("=======================================");
        System.out.println("1. Continue");
        System.out.println("2. Back");
        
        Iterator<Hobby> hobbyListIterator = hobbies.getIterator();
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 2);
        
        switch(choice) {
            case 1:
                int index = 1;
                System.out.println("Which hobby do you want to update? (Note: Enter the hobby no)");
                while(hobbyListIterator.hasNext()){
                    System.out.print(index + ". ");
                    System.out.println(hobbyListIterator.next());
                    index++;
                }
                
                // get index of the hobby to be updated
                int hobbyIndex;
                do {
                    hobbyIndex = UserInput();
                }while (hobbyIndex < 1 || hobbyIndex > index);
                scan.nextLine();
                String newHobbyName = scan.nextLine();
                
                // find hobby location and update it
                hobbies.replace(hobbyIndex, new Hobby(newHobbyName));
                member.setHobbyList(hobbies);
                memberList.replace(location, member);
                break;
            case 2:
                // automatically return
                break;
        }
        Hobbies(location);
    }
    
    public static void DeleteHobby(int location) {
        System.out.println("=======================================");
        System.out.println("Delete a hobby");
        System.out.println("=======================================");
        System.out.println("1. Continue");
        System.out.println("2. Back");
        
        Iterator<Hobby> hobbyListIterator = hobbies.getIterator();
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 2);
        
        switch(choice) {
            case 1:
                int index = 1;
                System.out.println("Which hobby do you want to delete? (Note: Enter the hobby no)");
                while(hobbyListIterator.hasNext()){
                    System.out.print(index + ". ");
                    System.out.println(hobbyListIterator.next());
                    index++;
                }
                
                // get index of hobby to be deleted
                int hobbyIndex;
                do {
                    hobbyIndex = UserInput();
                }while(hobbyIndex < 1 || hobbyIndex > index);
                
                // find hobby location and delete it
                hobbies.remove(hobbyIndex);
                member.setHobbyList(hobbies);
                memberList.replace(location, member);
                break;
            case 2:
                break;
        }
        Hobbies(location);
    }
    
    public static void ViewSharingPage(int location) {
        System.out.println("=======================================");
        System.out.println("Viewers Sharing Page");
        System.out.println("=======================================");
        System.out.println("1. Continue");
        System.out.println("2. Add your hobby to this page");
        System.out.println("3. Search sharing page");
        System.out.println("4. Download hobbies");
        System.out.println("5. Back");
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 5);
        
        switch(choice) {
            case 1:
                System.out.println("Press any key to continue...");
                int size = page.capacity() - 1;
                while(size >= 0) {
                    // get page at the current position and store into sharing
                    sharing = page.elementAt(size);
                    System.out.println("----------{" + sharing.getId()+ "}-----------");
                    System.out.println(sharing);
                    System.out.println("---------------------------\n");
                    scan.next();
                    size--;
                }
                ViewSharingPage(location);
                break;
            case 2:
                System.out.println("=======================================");
                System.out.println("Your current hobbies");
                System.out.println("=======================================");
                // loop this member's hobbies
                Iterator<Hobby> memberHobbyIterator = hobbies.getIterator();
                int index = 1;
                while(memberHobbyIterator.hasNext()) {
                    System.out.print(index + ". ");
                    System.out.println(memberHobbyIterator.next());
                    index++;
                }
                
                System.out.println("Which hobby do you want to add to the sharing page?");
                int chooseHobby;
                do {
                    chooseHobby = UserInput();
                }while(chooseHobby < 1 || chooseHobby > index);
                
                // add user's hobby to the sharing page
                Hobby hobby = member.getHobbyList().getEntry(chooseHobby);
                LocalDate currentDate = LocalDate.now();
                page.push(new Sharing(member.getName(), hobby, currentDate));
                
                ViewSharingPage(location);
            case 3:
                // implement search function to stack
                SearchSharedPage();
                ViewSharingPage(location);
                break;
            case 4:
                // implement download hobbies
                DownloadHobbies();
                ViewSharingPage(location);
                break;
            case 5:
                Hobbies(location);
                break;
        }
        
    }
    
    public static void SearchSharedPage() {
        System.out.println("=======================================");
        System.out.println("Search Sharing Page");
        System.out.println("=======================================");
        System.out.println("1. Search page based on author");
        System.out.println("2. Search page based on hobby name");
        System.out.println("3. Search page based on date");
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 3);
        
        switch(choice) {
            case 1:
                System.out.print("Enter the author's name to search for: ");
                scan.nextLine();
                String name = scan.nextLine();
                String nameResult = SearchByCriteria("name", name);
                System.out.println(nameResult);
                break;
            case 2:
                System.out.print("Enter the hobby name to search for: ");
                scan.nextLine();
                String hobbyName = scan.nextLine();
                String hobbyResult = SearchByCriteria("hobby", hobbyName);
                System.out.println(hobbyResult);
                break;
            case 3:
                System.out.print("Enter the date to search for (Format -> YYYY-MM-DD) : ");
                String date = GetValidDate();
                String dateResult = SearchByCriteria("date", date);
                System.out.println(dateResult);
                break;
        }
    }
    
    public static String GetValidDate() {
        boolean check = false;
        // regex to get valid date format
        String regex = "\\d{4}-\\d{1,2}-\\d{1,2}";
        LocalDate userDate = LocalDate.now();
        String date;
        scan.nextLine();
        
        do {
            date = scan.nextLine();
            try{
                // split date to array with 3 values ex: { "2022", "12", "31" }
                String[] split = date.split("-");
                
                userDate = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                
                // if date is more than current date then show an error
                if(userDate.compareTo(LocalDate.now()) > 0) {
                    System.out.println("Date cannot be later than the current date");
                }else {
                    check = true;
                }
            } 
            catch(DateTimeException ex) {
                // if date entered by user cannot be converted to LocalDate value
                System.out.println("Enter a valid date pls");
            } 
            catch(NumberFormatException ex) {
                // if user doesn't enter an integer
                System.out.println("Enter a number pls");
            } 
            catch(ArrayIndexOutOfBoundsException ex) {
                // if user doesn't incluce dashes in the date
                System.out.println("The format is YYYY-MM-DD");
            }
        }while(check == false || !date.trim().matches(regex));
        
        return date;
    }
    
    public static String SearchByCriteria(String criteria, String value) {
        // to avoid mistakes
        criteria = criteria.toLowerCase();
        
        Iterator<Sharing> pageIterator = page.getIterator();
        String result = "";
        
        while(pageIterator.hasNext()) {
            sharing = pageIterator.next();
            switch(criteria) {
                case "name":
                    if(sharing.getMemberName().trim().toLowerCase().equals(value.toLowerCase())) {
                        result += "\n----------{" + sharing.getId() + "}-----------\n";
                        result += sharing;
                        result += "\n---------------------------\n";
                    }
                    break;
                case "hobby":
                    if(sharing.getHobbyName().getName().toLowerCase().trim().equals(value.toLowerCase())) {
                        result += "\n----------{" + sharing.getId() + "}-----------\n";
                        result += sharing;
                        result += "\n---------------------------\n";
                    }
                    break;
                case "date":
                    if(sharing.getDateAdded().toString().equals(value)) {
                        result += "\n----------{" + sharing.getId() + "}-----------\n";
                        result += sharing;
                        result += "\n---------------------------\n";
                    }
                    break;
                case "all":
                        result += "\n----------{" + sharing.getId() + "}-----------\n";
                        result += sharing;
                        result += "\n---------------------------\n";
                        break;
            }
        }
        return result;
    }
    
    public static void DownloadHobbies() {
        System.out.println("=======================================");
        System.out.println("Download Hobbies");
        System.out.println("=======================================");
        System.out.println("1. Download all hobbies");
        System.out.println("2. Download based on author");
        System.out.println("3. Download based on hobby name");
        System.out.println("4. Download based on date");
        System.out.println("5. Back");
        
        int choice;
        do {
            choice = UserInput();
        }while(choice < 1 || choice > 5);
        
        switch(choice) {
            case 1:
                String allResult = SearchByCriteria("all", "");
                WriteToFile(allResult, "hobbies.txt");
                break;
            case 2:
                System.out.println("Enter the author's name to download");
                scan.nextLine();
                String name = scan.nextLine();
                String authorResult = SearchByCriteria("name", name);
                WriteToFile(authorResult, name);
                break;
            case 3:
                System.out.println("Enter the hobby name to download");
                scan.nextLine();
                String hobbyName = scan.nextLine();
                String hobbyResult = SearchByCriteria("hobby", hobbyName);
                WriteToFile(hobbyResult, hobbyName);
                break;
            case 4:
                System.out.println("Enter the date to download");
                String date = GetValidDate();
                String dateResult = SearchByCriteria("date", date);
                WriteToFile(dateResult, date);
                break;
            case 5:
                // automatically returns
                break;
        }
    }
    
    public static void WriteToFile(String result, String filename) {
        try {
            FileWriter file = new FileWriter(filename + ".txt");
            file.write(result);
            file.close();
            System.out.println("Successfully downloaded as " + filename + ".txt\n");
        }catch(IOException e) {
            System.out.println("An error occured.\n");
        }
    }
    
    public static void Exit() {
        scan.close();
        System.exit(0);
    }
    
    public static void CreateDefaultUsers() {
        ArrayStack<LocalDate> date = new ArrayStack<LocalDate>();
        date.push(LocalDate.of(1969, 11, 7));
        date.push(LocalDate.of(1976, 3, 24));
        date.push(LocalDate.of(2000, 10, 24));
        date.push(LocalDate.of(2005, 3, 15));
        date.push(LocalDate.of(2010, 12, 27));
        date.push(LocalDate.of(2011, 11, 28));
        
        date.push(LocalDate.of(2022, 1, 3));
        date.push(LocalDate.of(2003, 10, 1));
        date.push(LocalDate.of(2006, 3, 21));
        
        date.push(LocalDate.of(2013, 3, 22));
        date.push(LocalDate.of(2011, 4, 17));
        date.push(LocalDate.of(2004, 9, 6));
        
        // add a few hobbies for each member
        LinkedList<Hobby> hobbies1 = new LinkedList<Hobby>();
        hobbies1.add(new Hobby("Coding"));
        hobbies1.add(new Hobby("Eating"));
        hobbies1.add(new Hobby("Working out"));
        hobbies1.add(new Hobby("Playing game"));
        hobbies1.add(new Hobby("Sleeping"));
        hobbies1.add(new Hobby("Cooking"));
        memberList.add(new Member("Arisch", "pwd", hobbies1));
        
        LinkedList<Hobby> hobbies2 = new LinkedList<Hobby>();
        hobbies2.add(new Hobby("Basketball"));
        hobbies2.add(new Hobby("Playing game"));
        hobbies2.add(new Hobby("Badminton"));
        memberList.add(new Member("Lee Wen Zhuo", "h1234", hobbies2));
        
        LinkedList<Hobby> hobbies3 = new LinkedList<Hobby>();
        hobbies3.add(new Hobby("Drawing"));
        hobbies3.add(new Hobby("Football"));
        hobbies3.add(new Hobby("Playing game"));
        memberList.add(new Member("Ooi Guan Zhi", "Guan", hobbies3));
        
        /* 
        better to create another iterator since the first iterator is used in the function 
        VerifyUsers() which means if the iterator is used here then the next value will be null
        and the VerifyUsers() function can't check for the values so an iterator cannot be used twice
        */
        Iterator<Member> tempMemberIterator = memberList.getIterator();
        
        // add the users hobbies to sharing page
        int index = 0;
        while(tempMemberIterator.hasNext()) {
            member = tempMemberIterator.next();
            Iterator<Hobby> hobbyListIterator = member.getHobbyList().getIterator();
            while(hobbyListIterator.hasNext()) {
                page.push(new Sharing(member.getName(), hobbyListIterator.next(), date.elementAt(index)));
                index++;
            }
        }
    }
    
    public static void main(String[] args) {
        CreateDefaultUsers();
        Start();
    }
}
