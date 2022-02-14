package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // JPA가 관리할 객체
public class Member {

    @Id // JPA에게 pk가 뭔지 알려주는 역할(데이터베이스 PK와 매핑)t
    private Long id;
    private String name;

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
