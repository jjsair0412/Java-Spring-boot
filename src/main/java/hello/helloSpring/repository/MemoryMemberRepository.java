package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.apache.commons.logging.Log;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // 반환되는 값이 null일떄 optional이라는것으로 묶어서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        // stream은 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자이다.
        // store의 값을 하나씩 참조해서 람다식으로 반복해준다.
        return store.values().stream()
                // member.getName()과 같은 이름만 꺼내온다.
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
