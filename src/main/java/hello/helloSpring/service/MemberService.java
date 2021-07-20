package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.repository.MemoryMemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 검증 완료되면 memberRepository에 받아온 member를 저장
        return member.getId(); // 저장 후 id값만 반환
    }

    private void validateDuplicateMember(Member member){
        // 같은 이름을 가진 중복 회원은 있으면 안됀다.
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        /*
        위와 같은 내용의 코드이다.
        Optional<Member> result = memberRepository.findByName(member.getName());
        // ifPresent는 null이 아니라면 해당 람다식이 실행된다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

         */

    }

    // 전체 회원 조회
    public List<Member> findMmebers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
