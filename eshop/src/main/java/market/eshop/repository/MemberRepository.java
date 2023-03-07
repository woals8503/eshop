package market.eshop.repository;

import market.eshop.domain.Member;
import market.eshop.repository.custom.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

}
