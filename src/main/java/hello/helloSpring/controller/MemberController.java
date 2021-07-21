package hello.helloSpring.controller;

import hello.helloSpring.domain.Member;
import hello.helloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    public final MemberService memberService;

    // 의존객체 자동주입으로 memberService를 MemberController에 넣어준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // createForm과 create메서드는 서로다른 get방식과 post방식을 사용하기때문에,
    // POST방식으로 값을 넘겨주면 create쪽 메서드로 이동된다.
    // 또한 그냥 url로 members/new를 url에 작성하면 createForm으로 넘어가게 된다.
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // 사용자가 입력한 이름은 노출되면 안돼기때문에 PostMapping 사용
    @PostMapping("/members/new")
    public String create(MemberForm form){
        // member객체 생성
        Member member = new Member();
        member.setName(form.getName());

        System.out.print("member : " +member.getName());
        memberService.join(member);

        // 리다이렉트로 post방식의 값이 전달되면 root 페이지로 보낸다.
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMmebers();
        model.addAttribute("members",members);
        return "members/memberList";

    }
}
