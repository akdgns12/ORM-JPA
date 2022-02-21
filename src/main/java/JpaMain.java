import hellojpa.Book;
import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

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
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1.getClass() = " + m1.getClass());

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());


            System.out.println("a == a :" + (m1 == reference));

            em.flush();
            em.clear();

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

