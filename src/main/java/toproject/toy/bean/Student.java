package toproject.toy.bean;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Student {
    private Long id;
    private String firstName;
    private String lastName;

    // 생성자
    public Student(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
