package entity;

import java.time.LocalDate;

public class Sharing {
    private int id;
    private String memberName;
    private Hobby hobbyName;
    private LocalDate dateAdded;
    private static int nextId = 3000;

    public Sharing() {
        id = nextId++;
    }

    public Sharing(String memberName, Hobby hobbyName, LocalDate dateAdded) {
        id = nextId++;
        this.memberName = memberName;
        this.hobbyName = hobbyName;
        this.dateAdded = dateAdded;
    }
    
    public int getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public Hobby getHobbyName() {
        return hobbyName;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    @Override
    public String toString() {
        return "Name: " + memberName + "\nHobby Name: " + hobbyName + "\nDate Added: " + dateAdded;
    }
}
