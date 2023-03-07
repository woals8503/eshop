package market.eshop.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import market.eshop.domain.Member;
import market.eshop.domain.form.LoginForm;
import market.eshop.repository.custom.MemberRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static market.eshop.domain.QMember.*;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Member> findByEmail(String email) {
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.email.eq(email))
                .fetch();
        return result;
    }

    @Override
    public List<Member> findByEmailAndPassword(LoginForm form) {
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.email.eq(form.getEmail())
                        .and(member.password
                                .eq(form.getPassword())))
                .fetch();

        return result;
    }
}
