package studiii.zlsj_test.enum_;

/**
 * @Description: test
 * @Author: liaosijun
 * @Time: 2019/6/20 10:55
 */
public class Test {

	public static void main(String[] args) {
		/*String a = null;
		String b = "";
		String c = "  ";
		System.out.println(StringUtils.isEmpty(a));
		System.out.println(StringUtils.isEmpty(b));
		System.out.println(StringUtils.isEmpty(c));*/


		System.out.println("原始数据：");
		for (Season season : Season.values()) {
			System.out.println(season);
		}
		System.out.println("-----------------------------");
		DynamicEnumUtils.addEnum(Season.class, "WINTER", new Class[] {
				String.class, SeasonPattern.class }, new Object[] {
				"winter", SeasonPattern.SNOW });
		System.out.println("添加后的数据：");
		for (Season season : Season.values()) {
			System.out.println(season);
		}
		System.out.println("-----------------------------");
		Season season = Season.valueOf("WINTER");
		System.out.println("新添加的枚举类型可以正常使用：");
		System.out.println(season.name());
		System.out.println(season.getKey());
		System.out.println(season.getSeasonPattern());
	}

	/**
	 * 季节特征，用作参数传递的列子
	 */
	public enum SeasonPattern {

		BEAR, HOT, WITHERED, SNOW;
	}

	/**
	 * 季节
	 */
	public enum Season {

		SPRING("spring", SeasonPattern.BEAR), SUMMER("summer", SeasonPattern.HOT), AUTUMN(
				"autumn", SeasonPattern.WITHERED);

		private final String key;
		// 季节特征
		private final SeasonPattern sp;
		private Season(String key, SeasonPattern sp) {
			this.key = key;
			this.sp = sp;
		}
		public String getKey() {
			return this.key;
		}
		public SeasonPattern getSeasonPattern() {
			return sp;
		}
	}
}