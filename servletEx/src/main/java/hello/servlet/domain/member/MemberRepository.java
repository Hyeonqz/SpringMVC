package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 동시성 문제가 고려되어 있지 않아, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
* */
public class MemberRepository {
	private Map<Long,Member> store = new HashMap<Long,Member>();
	private static long sequence = 0L;


	//싱글톤 패턴
	private static final MemberRepository instance = new MemberRepository();
	public static MemberRepository getInstance() { //싱글톤 생성시, 생성자를 private으로 하여
		//아무나 생성자 못만들게 설정함.
		return instance;
	}
	private MemberRepository() {

	}

	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	public Member findById(Long id) {
		return store.get(id);
	}

	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	public void clearStore() {
		store.clear();
	}

}
