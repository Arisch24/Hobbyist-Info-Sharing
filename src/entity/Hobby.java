package entity;

public class Hobby {
    private int id;
    private String name;
    private static int nextId = 2000;

    public Hobby() {
        id = nextId++;
    }
    
    public Hobby(String name) {
        id = nextId++;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}