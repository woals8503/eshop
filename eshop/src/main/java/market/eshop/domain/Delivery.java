package market.eshop.domain;

import lombok.Getter;
import lombok.Setter;
import market.eshop.domain.status.DeliveryStatus;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;


}