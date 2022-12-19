package toproject.toy.entity.practice;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "orders")
    private List<Order> orders = new ArrayList<Order>();
}
