package market.eshop.domain.base;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)  // 이벤트 기반으로 동작
@MappedSuperclass   // 테이블에 반영
@Getter
public class BaseEntity extends BaseTimeEntity {

    @CreatedBy
    @Column(updatable = false)  // 생성자 수정 불가
    private String createdBy;

    //수정자
    @LastModifiedBy
    private String lastModifiedBy;
}
