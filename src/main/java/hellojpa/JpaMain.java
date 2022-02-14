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
        tx.begin();

        /*
            JPA를 통해서 앤티티를 가져오면 JPA가 앤티티를 관리한다.
            JPA가 변경 여부를 트랜잭션 커밋하기 전에 체크하고
            변경된 부분이 있다면 update쿼리를 날린다
         */
        try {
//            Member findMember = em.find(Member.class, 1L);
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 앤티티 매니저는 쓰레드간에 공유 X (사용하고 버려야 한다)
            em.close();
        }

        emf.close();
    }
}
