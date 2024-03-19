package managestudentsapplication;

public class Student extends BaseEntity implements DataEntry{
    private String name;
    private String id;

    public Student(String name, String code) {
        this.name = name;
        this.id = code;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String code) {
        this.id = code;
    }  

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", id=" + id + '}';
    }
    
    
}
