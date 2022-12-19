package toproject.toy.entity.practice;

import lombok.Getter;
import lombok.Setter;
import toproject.toy.entity.practice.entityEnum.OrderStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Temporal(value = TemporalType.DATE)
    private Date orderDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
}
