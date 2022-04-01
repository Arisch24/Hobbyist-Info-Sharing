package entity;

import adt.LinkedList;

public class Member {
    private int id;
    private String name;
    private String password;
    private LinkedList<Hobby> hobbyList;
    private static int nextId = 1000;
    
    public Member() {
        id = nextId++;
        hobbyList = new LinkedList<Hobby>();
    }

    public Member(String name, String password) {
        id = nextId++;
        this.name = name;
        this.password = password;
        hobbyList = new LinkedList<Hobby>();
    }
    
    public Member(String name, String password, LinkedList<Hobby> hobbyList) {
        id = nextId++;
        this.name = name;
        this.password = password;
        this.hobbyList = hobbyList;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }

    public LinkedList<Hobby> getHobbyList() {
        return hobbyList;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setHobbyList(LinkedList<Hobby> hobbyList) {
        this.hobbyList = hobbyList;
    }

    @Override
    public String toString() {
        return "Member{" + "name=" + name + ", password=" + password + ", hobbyList=" + hobbyList + '}';
    }
}