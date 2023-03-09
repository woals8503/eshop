package market.eshop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import market.eshop.domain.embadded.Address;
import market.eshop.domain.status.DeliveryStatus;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Builder
    public Delivery(Address address) {
        this.address = address;
        this.status = DeliveryStatus.READY;
    }
}
