package toproject.toy.entity.practice;

import javax.persistence.*;

@Entity
@Table(name = "orderitems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;


    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    private Integer orderPrice;
    private Integer count;


}
