package pl.sda.jdbcjpa.person;

import com.google.common.collect.Lists;
import lombok.*;
import pl.sda.jdbcjpa.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String surname;

    private String city;

    private String street;

    private String postalCode;

    @Transient
    private String thisFieldIsNotToPersist;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Order> ordersList = Lists.newArrayList();
}
