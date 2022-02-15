package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // JPA가 관리할 객체, JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
//@Table(name = "MBR") // 엔티티와 매핑할 테이블 지정
public class Member {

    @Id
    private Long id;

    @Column(name = "name") // 객체는 username을 쓰고 싶은데, 컬럼에는 name이라 쓰고 싶을 때 @Column을 사용해서 컬럼명 지정가능
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING) // enum 타입 매핑, EnumType.ORDINAL -> enum 순서를 DB에 매핑 사용하면 XXX -> 그냥 무조건 EnumType.STRING 사용하자 = enum 이름을 DB에 매핑
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // 날짜 타입 매핑
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob // CLOB, BLOB 매핑
    private String description;

    @Transient // 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
    private int temp;

    public Member() {
    }
//    @Id // JPA에게 pk가 뭔지 알려주는 역할(데이터베이스 PK와 매핑)
//    private Long id;
//    private String name;
//    // *주의 - 기본 생성자 필수(파라미터가 없는 public 또는 protected 생성자)
//    public Member(){
//    }
//
//    public Member(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
