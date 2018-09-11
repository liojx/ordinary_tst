package studiii.zlsj_test.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class Digo {
	private final String BIG_DOG_NAME = "blackApple";
	
	private String name = BIG_DOG_NAME;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
