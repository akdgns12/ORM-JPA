package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id @GeneratedValue
    private Long id;
    private String name;

    /*
        CASCADE - 영속성 전이,
        특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속상태로 만들고 싶을 때
        ex) 부모 엔티티를 저장할 떄 자식 엔티티도 함꼐 저장.
        주의 !
        - 영속성 전이는 연관관계를 매핑하는 것과 아무 관련이 없음
        - 엔티티를 영속화할 떄 연관된 엔티티도 함께 영속화하는 편리함을 제공할 뿐뿐
     */
    // orphanRemoval
    // 참조가 제거된 엔티티는 다른 곳에서 참조하지 않는 고아객체로 보고 삭제하는 기능
    // 참조하는 곳이 하나일 떄 사용해야함!!, 특정 엔티티가 개인 쇼유할 떄 사용!!
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval - 고아 객체(부모-자식 관계에서 부모엔티티와 연관계계가 끊긴 자식엔티티)를 자동 삭제해주는 기능
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
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
