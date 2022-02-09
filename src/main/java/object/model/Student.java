package object.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String dateOfBirth;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String status; //Thôi học, đình chỉ, chờ chuyển lớp, đang học

    @ManyToOne
    private Class classModel;

    @OneToMany
    private List<Module> moduleList;

}
