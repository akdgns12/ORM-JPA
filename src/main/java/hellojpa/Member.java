package hellojpa;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    /**
     * 가급적 지연로딩만 사용하자!(특히 실무에서)
     * @ManyToOne, @OneToOne 은 default가 EAGER -> LAZY로 바꿔줘야
     * 즉시로딩은 예상하지 못한 SQL 쿼리가 나가므로 실무에서는 LAZY로 다 설정해주고
     * 같이 조회할 필요가 있을 경우에 JPQL - fetch join을 그때마다 사용하자
     */
    // 즉시로딩 EAGER를 사용해서 프록시를 사용하지 않고 실제 엔티티를 사용한 ex) Member조회시 항상 Team도 조회해야하는 상황이 많다면 EAGER사용하는 것이 유리
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 LAZY를 사용해서 프록시로 조회 ex) Member와 Team을 같이 조회할 일이 별로 없다면 LAZYf를 사용해 프록시를 통해 Team을 조회하는 것이 유
    @JoinColumn
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    //기간 - Period
    @Embedded // 값타입을 사용하는 곳에 표시, 참고로 임베디드 값타입이 null이면 매핑한 컬럼 모두 null
    private Period workPeriod;

    //주소 - Address
    @Embedded
    private Address homeAddress;

    // 한 엔티티에서 같은 값 타입을 사용하려면?
    // 컬럼 명이 중복됨 -> 그래서 @AttributeOverride: 속성 재정의 어노테이션 사용하면 가능
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city",
            column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street",
                    column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode",
                    column = @Column(name = "WORK_ZIPCODE"))
        })
    private Address workAddress;

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
