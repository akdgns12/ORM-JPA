package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 조인전략 : @Inheritance(strategy = InheritanceType.JOIN), 구현 클래마다 테이블 전략 : @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS), 부모가 되는 Item테이블은 만들지 않고 Album, Moive, Book들 같은 구현 클래스마다 테이블을 생성하는 전략
@DiscriminatorColumn // 단일테이블 전략에서는 이 어노테이션 없어도 DTYPE이 생성됨 - 단일테이블에 데이터들이 모여있으니 어느 테이블의 데이터인지 구분할 DTYPE이 있어야하므로
public abstract class Item extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
