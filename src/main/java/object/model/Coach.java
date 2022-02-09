package object.model;


import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Coach")
public class Coach{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String phone;
    private String img;
    private String address;

    @OneToMany
    private List<Class> classList;

    @OneToMany
    private List<Student> studentList;

    private String reflection;
}
