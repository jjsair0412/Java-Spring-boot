package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각각의 테스트 메서드들은, 순서에 의존적이지 않게 설계해야한다.
    // 따라서 한 테스트의 수행 결과가 다른 메서드 테스트 수행 결과에 영향을 줄 수 있다.
    @AfterEach
    // 그래서 AfterEach라는 어노테이션을 가진 메서드를 사용하여, 각 메서드 테스트가끝난후
    // 메모리를 clear해주는 기능이 필요하다.
    public void afterEach(){
        // 메모리를 clear해주는 clearStore는 MemoryMemberRepository에 존재한다.
        repository.clearStore();
    }
    // save 메서드 테스트
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        // Optional에서 값을 꺼내올땐 get으로 꺼내오면 된다,
        Member result = repository.findById(member.getId()).get();
        // member와 result가 같다면 초록불, 다르다면 빨간불이 나온다.
        Assertions.assertEquals(member,result);

    }
    // findByName 메서드 테스트트
   @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertEquals(member1,result);


        // spring2를 찾는다면 다른 객체로 인식하여 빨간줄 생성
//       Member result = repository.findByName("spring2").get();
//       Assertions.assertEquals(member1,result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member1);

        List<Member> result = repository.findAll();
        // 기대한 size는 3인데 result.size는 2라서 빨간불 출력
  //      Assertions.assertEquals(3,result.size());
        Assertions.assertEquals(2,result.size());
    }
 }

