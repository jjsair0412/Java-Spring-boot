package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // insert문 실행
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // select문 실행, id값을 가져오는 select
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // Member 객체자체를 select한다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
