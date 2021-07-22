package hello.helloSpring;

import hello.helloSpring.repository.JdbcMemberRepository;
import hello.helloSpring.repository.JpaMemberRepository;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 자바 코드를 활용해서 bean객체를 생성하는방법
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private DataSource dataSource;
    private EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository){
        this.dataSource = dataSource;
        this.em = em;
        this.memberRepository = memberRepository;
    }

    //컨테이너에 bean객체 생성
    @Bean
    public MemberService memberService() {
        // memberService의 기본생성자의파라미터는 MemberRepository()를 받아야하기 떄문에ㅔ
        // 밑에 만들어놧던 bean객체를 넣어준다.
        return new MemberService(memberRepository());
    }

    // 자바를 사용해서 bean객체를 만들어준다면, new키워드로 저것만 바꿔주면 된다
    //
    @Bean
    public MemberRepository memberRepository() {
//      return new MemoryMemberRepository();
        // JdbcMemberRepository와 MemoryMemberRepository는 모두 MemberRepository interface를
        // implements해주었기 떄문에, 반환타입이 동일하여 반환할 수 있다.
        // 또한 스프링 컨테이너에 MemberRepository bean객체가 생성되면서,
        // jdbc와 연결하는 dao가 반환되기 때문에, 기존 메모리에 저장했던 방법에서 db에 저장하는
        // 방법으로 바꾸는 과정이 간단해질수 있었다.
        // 이런 방법이 개방-폐쇄 원칙(OCP, Open-Closed Principle)이다.
 //       return new JdbcMemberRepository(dataSource);
//        return new JdbcMemberRepository(dataSource);
//        return new JpaMemberRepository(em);

    }
}
