package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA가 관리할 객체, JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
//@Table(name = "MBR") // 엔티티와 매핑할 테이블 지정
public class Member {

    @Id // JPA에게 pk가 뭔지 알려주는 역할(데이터베이스 PK와 매핑)
    private Long id;
    private String name;
    // *주의 - 기본 생성자 필수(파라미터가 없는 public 또는 protected 생성자)
    public Member(){
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
