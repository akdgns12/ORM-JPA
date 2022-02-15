package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 앤티티 매니저 팩토리는 하나만 생성해서 프로젝트 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 앤티티 매니저는 쓰레드간에 공유 X (사용하고 버려야 한다)
        EntityManager em = emf.createEntityManager();
        // JPA의 모든 데이터 변경은 Transaction안에서 실행!
        EntityTransaction tx = em.getTransaction();
        // 앤티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.
        tx.begin(); // (트랜잭션) 시작

        /*
            JPA를 통해서 앤티티를 가져오면 JPA가 앤티티를 관리한다.
            JPA가 변경 여부를 트랜잭션 커밋하기 전에 체크하고
            변경된 부분이 있다면 update쿼리를 날린다
         */
        try {
//            Member findMember = em.find(Member.class, 1L);
            /*
               JPQL은 엔티티 객체를 대상으로 쿼리
               SQL은 데이터베이스 테이블을 대상으로 쿼리
             */
//            // 객체를 생성한 상태(비영속)
//           1 Member member = new Member();
//            member.setId(100L);
//            member.setName("Hello");
//
//            // 객체를 저장한 상태 - DB에 저장한게 아니라 영속성 컨텍스트에 저장한 상태!(영속)
//            em.persist(member);
              // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
//            em.detach(member);
              // 객체를 삭제한 상태(삭제)
//            em.remove(member);

//            List<Member> result = em.createQuery("select m from Member as m", Member.class) // JPQL은 테이블이 아니라 객체를 대상으로 잡고 쿼리를 사용(객체를 대상으로 하는 객체지향 쿼리)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }

//            // 객체를 생성한 상태(비영속)
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("Hello");
//
//            // 객체를 저장한 상태 - DB에 저장한게 아니라 영속성 컨텍스트에 저장한 상태!(영속)
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");
//            // 앤티티매니저는 DB보다 먼저 1차캐시(영속성컨텍스트)를 조회, 1차캐시에 해당 member가 없다면 DB를 조회하고 DB에 있다면 1차캐시에 저장
//            Member findMember = em.find(Member.class, 101L);
//
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

//            // 영속 엔티티 동일성 보장
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            System.out.println("result = " + (findMember1 == findMember2)); // 동일성 비교 true
//            // 앤티티 등록
//            // 트랜잭션을 지원하는 쓰기 지연
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
            // 여기까지 insert sql을 DB에 보내지 않는다

//            // 영속 엔티티 조회
//            Member findMember = em.find(Member.class, 150L);
//            // 영속 엔티티 데이터 수정
//            findMember.setName("ZZZZ");

            // em.persist(findMember); // 이런 코드가 있어야 하지 않을까?
            // -> JPA는 dirty checking(변경감지)을 통해 변경 사항을 일일히 체크하여 변경된 점이 있다면 트랜잭션을 commit하는 시점에 update쿼리를 날려서 변경을 DB에 반영

            // 커밋하는 순간 DB에 insert sql을 보낸다.
            tx.commit(); // (트랜잭션) 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 앤티티 매니저는 쓰레드간에 공유 X (사용하고 버려야 한다)
            em.close();
        }

        emf.close();
    }
}
