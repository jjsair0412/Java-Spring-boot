package hello.helloSpring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
// AOP를 사용하기 위해서 aopclass에 어노테이션 @Aspect를 넣어준다
//@Component
// 그리고 해당 class를 Springbean으로 컨테이너에 등록해주어야한다.
public class TimeTraceAop {


    @Around("execution(* hello.helloSpring..*(..))")
    // Around라는 어노테이션으로 공통 관심사항의 기능이 어디에 들어가야하는지 지정해준다.
    // 여기선 hello.helloSpring의 하위 페키지에는 전부다 적용하는것으로 설정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        // 공통관심사항에 들어갈 구문들 작성
        long start = System.currentTimeMillis();
        System.out.print("START : "+ joinPoint.toString());
        try{

        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.print("END : "+ joinPoint.toString()+ " "+ timeMs + "ms");
        }

         Object result = joinPoint.proceed();
        return result;
    }
}
