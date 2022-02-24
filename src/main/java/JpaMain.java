import hellojpa.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // JPQL - 테이블이 아닌 앤티티 객체를 대상으로 객체지향 쿼리
            // SQL을 추상화해서 특정 DB에 의존하지 X
            // JPQL - 한마디로 정의하면 객체지향 SQL
            List<Member> result = em.createQuery("select m From Member m where m.username like '%kim'", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member);
            }
//            Member member = new Member();
//            member.setName("member1");
//            member.setHomeAddress(new Address("homeCity","street","10000"));
//
//            // 값타입 컬렉션은 : 영속성 전이(CASCADE) + 고아객체 제거를 필수로 가진다고 볼 수 있다. why?
//            // 값타입은 자체적인 생명주기가 없고 Member의 생명주기를 따라가기 때문
//
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("족발");
//            member.getFavoriteFoods().add("피자");
//
//            member.getAddressesHistory().add(new AddressEntity("old1", "street", "10000"));
//            member.getAddressesHistory().add(new AddressEntity("old2", "street", "10000"));
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            System.out.println("============START==========");
//            Member findMember = em.find(Member.class, member.getId()); //값타입 컬렉션도 지연로딩 전략을 사용한다
//
//            // 값타입 수정
//            //homeCity -> newCity
////            findMember.getHomeAddress().setCity("newCity");  값타입 수정은 이렇게 하면 안된다!!!! -> 부작용 발생
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode())); // 이렇게 완전히 새로운 인스턴스로 갈아끼워서 수정하는게 맞
//
//            // 값타입 컬렉션 수정
//            // 치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
//
//            findMember.getAddressesHistory().remove(new AddressEntity("old1", "street", "10000"));
//            findMember.getAddressesHistory().add(new AddressEntity("newCity1", "street", "10000"));

            // 값타입 조회
//            List<Address> addressHistory = findMember.getAddressesHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }


//            Address address = new Address("city", "street", "10000");
//
//            Member member = new Member();
//            member.setUsername("member");
//            member.setHomeAddress(address);
//            em.persist(member);
//
//            //그래서 값타입의 실제 인스턴스인 값을 공유하는 것은 위험하기 때문에
//            //이런식으로 값(인스턴스)를 복사해서 사
//            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
//
//            Member member2 = new Member();
//            member2.setUsername("member");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);
//
//            //개발자는 member의 city만 바꾸고자 했겠지만 같은 address(임베디드 값타입)을 사용하고 있는 member2의 city까지 바뀐다
//            //임베디드 타입 같은 값타입을 여러 엔티티에서 공유하면 위험함 - 부작용 발
//            member.getHomeAddress().setCity("newCity");

//            Child child1 = new Child();
//            Child child2 = new Child();
//
//            Parent parent = new Parent();
//            parent.addChild(child1);
//            parent.addChild(child2);
//
//            // CASCADE - 영속성 전이를 사용하면 부모 객체만 persist해도 자식 객체까지 모두 persist할 수 있다
//            em.persist(parent);
////            em.persist(child1);
////            em.persist(child2);
//
//            em.flush();
//            em.clear();
//
//            Parent findParent = em.find(Parent.class, parent.getId());
//            findParent.getChildList().remove(0); // 자식 엔티티를 컬렉션에서 제거(orphanremoval의 기능으로!)

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member1 = new Member();
//            member1.setUsername("member1");
//            member1.setTeam(team);
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            // Member m = em.find(Member.class, member1.get(Id));
//
//            // 즉시로딩은 JPQL에서 N+1문제를 일으킨다 (쿼리 1개가 나갈떄 추가쿼리가 N개가 나가는 문제)
//            List<Member> members = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();
//
//            // SQL : select * from Member
//            // SQL : select * from Team WHERE TEAM_ID = xxxx


            /*
                *중요 프록시 특징
                - 프록시 객체를 초기화 할 때, 프록시 객체가 실제 엔티티로 바뀌는 것은 아님.
                초기화 되면 프록시 객체를 통해서 실제 엔티티에 접근 가능한 것
                - 프록시 객체는 원본 엔티티를 상속받음. 따라서 타입 체크시 주의해야함
                  (== 비교 하면 안됨!!, 대신 instance of 사용)
                - 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티 반환
                - 영속성 컨텍스의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기하면 문제발생
                 (하이버네이트는 org.hibernate.LazyInitializationException 예외를 터트림)
             */
//            Member member1 = new Member();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            Member m1 = em.find(Member.class, member1.getId());
//            System.out.println("m1.getClass() = " + m1.getClass());
//
//            Member reference = em.getReference(Member.class, member1.getId());
//            System.out.println("reference = " + reference.getClass());
//
//
//            System.out.println("a == a :" + (m1 == reference));


//            Member findMember = em.find(Member.class, member.getId());
            // getReference - 데이터를 실제 사용하는 시점에 쿼리를 날려 찾아옴(DB 데이터 조회를 미루는 가짜(프록시)앤티티 객체 조회
            /*
                프록시 특징
                - 실제 클래스를 상속 받아서 만들어짐
                - 실제 클래스와 겉 모양이 같다
                - 사용하는 입장에서 진찌 객체인지 프록시 객체인지 구분하지 않고 사용하면됨(이론상)
             */

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

