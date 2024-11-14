package data;

/**
 * The `Person` class in Java represents a person with a name attribute and getter/setter methods
 * for the name.
 */
public class Person {
    private String name;

    public Person() {
    }

    public Person(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
