package studiii.zlsj_test.service;

import org.springframework.stereotype.Service;

@Service
public class HitService {
	
	private String name;
	
	public String hit(String name) {
		System.out.println("hit one peace-->"+name);
		System.out.println(111);
		return "hit one peace-->"+name;
	}
}
