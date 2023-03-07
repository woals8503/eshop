package market.eshop.domain;

import lombok.Getter;
import lombok.Setter;
import market.eshop.domain.base.BaseEntity;
import market.eshop.domain.embadded.Address;
import market.eshop.domain.status.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;
    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
