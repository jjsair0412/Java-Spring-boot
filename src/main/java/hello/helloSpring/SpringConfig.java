package hello.helloSpring;

import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.repository.MemoryMemberRepository;
import hello.helloSpring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드를 활용해서 bean객체를 생성하는방법
@Configuration
public class SpringConfig {

    //컨테이너에 bean객체 생성
    @Bean
    public MemberService memberService() {
        // memberService의 기본생성자의파라미터는 MemberRepository()를 받아야하기 떄문에ㅔ
        // 밑에 만들어놧던 bean객체를 넣어준다.
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
