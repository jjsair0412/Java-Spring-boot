package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA를 사용하기 위해선, JpaRepository를 상속받아야 한다.
// JpaRepository는 value로 Entity 클래스와 id값의 데이터타입을 순서대로 value로 가지게 되고
// service가 정의되어있기만 한 interface를 상속받는다.
// 이 두가지를 먼저 상속시키는것이 사용하기위한 첫번째 단계이다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {

    // 스프링 데이터 jpa를 이렇게 만들어놓으면, 자동으로 bean을 컨테이너에 생성시켜준다.
    // 그 후 MemberRepository를 자바코드로 bean에 자동주입시켜놓는다면 자동으로 주입되게 된다.
    // 스프링데이터jpa는 기본적인 crud를 다 제공해주게 된다.
    // 그러나 select문에서 특정조건을 가지고 검색하는 기능은 제공하지 않는다.
    // 비즈니스 로직에 따라 다 다른 sql문이 생성되기 때문.
    // 따라서 findByName을 이용해서 특정시켜주게 된다.
    @Override
    // findByName은 sql문으로 select m from Member m where m.name = ?
    // 이런식의 sql문으로 작성되게 된다.
    Optional<Member> findByName(String name);
}
