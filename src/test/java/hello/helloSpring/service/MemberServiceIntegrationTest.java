package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
// Spring을 테스트할떄
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    // 테스트는 한글이름으로 메서드이름을 지정해주어도 된다.
    void 회원가입() {
        // given (이것을 줫을때)
        Member member=new Member();
        member.setName("hello");
        // when (이것을 실행하고 검증해서)
        Long saveId = memberService.join(member);

        // then ( 이렇게 나와야한다.)
        Member findMember = memberService.findOne(saveId).get();
        // member.getName()과 findMember.getName()이 같느냐
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    // 중복된 회원이 가입했을 경우 예외처리가 잘 되는지 확인한다
    public void 중복_회원_예외(){
        // given
            Member member1 = new Member();
            member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        // when

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        /*
        try{
            memberService.join(member2);
            fail("예외가 발생해야 합니다");
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
        }
        */

        // then

    }

}