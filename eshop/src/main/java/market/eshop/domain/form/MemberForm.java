package market.eshop.domain.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class MemberForm {

    @NotNull
    private String email;
    @NotNull
    private String username;

    @NotNull
    private String password;

    private String city;
    private String street;
    private String zipcode;
}
