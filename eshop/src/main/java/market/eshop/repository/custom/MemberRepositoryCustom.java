package market.eshop.repository.custom;

import market.eshop.domain.Member;
import market.eshop.domain.form.LoginForm;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findByEmail(String email);

    List<Member> findByEmailAndPassword(LoginForm form);

}
