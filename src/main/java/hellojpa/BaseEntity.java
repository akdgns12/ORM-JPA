package hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 상속관계 매핑 X, 엔티티 X, 테이블과 매핑 X
/*
    부모 클래스를 상속받는 자식클래스에 매핑정보만 제공
    조회, 검색(em.find(BaseEntity) 불가)
    직접 생성해서 사용할 일이 없으므로 추상 클래스 권장

    테이블과 관계없고, 단순히 엔티티가 공통으로 사용하는 매핑정보를 모으는 역할
    주로 등록일, 수정일, 등록자, 수정자 같은 전체 엔티티에서 공통적으로 적용하는 정보를 모을 때 사용
    참고 : @Entity 클래스는 엔티티나 @MappedSuperclass로 지정한 클래스만 상속 가능
 */
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModified;
    private LocalDateTime lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
