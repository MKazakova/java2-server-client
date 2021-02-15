import java.io.Serializable;

public class Cat implements Serializable {
    private int age;
    private String name;

    public Cat(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return name+" "+age;
    }
}
