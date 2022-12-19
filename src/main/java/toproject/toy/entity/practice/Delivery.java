package toproject.toy.entity.practice;

import lombok.Getter;
import lombok.Setter;
import toproject.toy.entity.practice.entityEnum.DeliveryStatus;

import javax.persistence.*;

@Entity
@Table(name = "deliveries")
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="deliveries")
    private Order order;

    private String city;
    private String street;
    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
}
