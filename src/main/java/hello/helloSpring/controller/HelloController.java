package hello.helloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping(value = "hello")
    public String Hello(Model model){

        model.addAttribute("data", "hello!");
        return "hello";
    }

    // mvc 방식
    // Get방식으로 메핑하는 어노테이션
    @GetMapping("hello-mvc")
    // RequestParam을 사용해서 name에 값을 받아온다.
    // 받아온값을 addAttribute를 사용해서 String name에 넣어주고 view로 값을 전달한다.
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){

     model.addAttribute("name",name);
     return "hello-template";

    }

    // api 방식
    @GetMapping("hello-string")
    @ResponseBody
    // ResponseBody는 내가 입력한 문자가 그대로 넘어간다는 뜻이다.
    // mvc와 템플릿의 방법과의 차이점은, view가 없다.
    // 받아온 name값을 그대로 출력해준다.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    // api방식에서 데이터를 전달하는 방식 (json으로 값을 전달)
    @GetMapping("hello-api")
    @ResponseBody
    // ResponseBody가 붙어있지 않으면 view 리졸버에게 전달
    // ResponseBody 어노테이션이 붙어있다면 http응답에 해당 데이터를 그냥 전달한다.
    // String이라면 그냥 String을 전달한다.
    // 그런데 Hello 메서드는 객체를 반환하는데,
    // 반환할때 Spring에서는 HttpMessageConverter가 동작한다.
    // HttpMessageConverter는 반환값이 객체면 JsonConverter가 json형식으로 변경하여 웹브라우저에 보내준다.
    public Hello helloApi(@RequestParam("name") String name){
        // 작성해줫던 Hello 클래스 객체를 생성해준다.
        Hello hello = new Hello();
        // hello에 받아온 name값을 set해준다.
        hello.setName(name);
        // hello 객체를 반환한다.
        return hello;
    }

    // name의 getset을 가지고있는 class를 작성한다.
    static class Hello{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
