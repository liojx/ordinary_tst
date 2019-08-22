package studiii.zlsj_test.java8;

import org.assertj.core.util.Lists;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author: liaosijun
 * @Time: 2019/5/7 14:42
 */
public class LamdaForListTest {

	public class TestClass{
		private String uui;
		private String code;
		private String name;

		public String getUui() {
			return uui;
		}

		public void setUui(String uui) {
			this.uui = uui;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	private static List<Integer> listA = Lists.newArrayList();
	private static List<Integer> listB = Lists.newArrayList();
	static {
		listA.add(1);
		listA.add(3);
		listA.add(5);
		listA.add(6);
		listA.add(9);
		listA.add(10);
		listA.add(6);
	}

	static {
		listB.add(3);
		listB.add(6);
		listB.add(345);
		listB.add(10);
	}

	public static List<TestClass> onDealList = Lists.newArrayList();

	public void fillClassList() {
		TestClass testClass1 = new TestClass();
		testClass1.setCode("BBC");
		testClass1.setName("L");
		testClass1.setUui("NCJFFFF");
		onDealList.add(testClass1);

		TestClass testClass2 = new TestClass();
		testClass2.setCode("JJV");
		testClass2.setName("L");
		testClass2.setUui("NCJFFFF");
		onDealList.add(testClass2);

		TestClass testClass3 = new TestClass();
		testClass3.setCode("BBB");
		testClass3.setName("L");
		testClass3.setUui("NCJFFFF");
		onDealList.add(testClass3);

		TestClass testClass4 = new TestClass();
		testClass4.setCode("KKC");
		testClass4.setName("L");
		testClass4.setUui("NCJFFFF");
		onDealList.add(testClass4);
	}

	public static String[] excludeCode = {"KKC","BBC"};

	static void filterTest(){
//		listA.stream().filter();
//		System.out.println(listA);
//		listA.stream().anyMatch(l -> listB.contains(l));

		// 取交集
		System.out.println(listB.retainAll(listA));
		System.out.println(listA);
		System.out.println(listB);
	}

	public static void main(String[] args) {
		System.out.println("--------filterTest----------------sss--------");
		filterTest();
		System.out.println("--------filterTest----------------eee--------");

		System.out.println("-----------------------xxxx---------------------");
		LamdaForListTest lamdaForListTest = new LamdaForListTest();
		lamdaForListTest.fillClassList();
		List<String> jj = Arrays.asList(excludeCode);
		List<TestClass> abclist = onDealList.stream().filter(a->!jj.contains(a)).collect(Collectors.toList());
		abclist.stream().forEach(c->{
			System.out.println("after filter-->" + c.getCode());
		});
		System.out.println("-----------------------xxxx---------------------");
		ArrayList<String> alllist = new ArrayList<>();
		alllist.add("1");
		alllist.add("1a");
		alllist.add("1b");
		alllist.add("1c");
		alllist.add("2c");
		alllist.add("2c");
		alllist.add("23c");
		alllist.add("24c");
		alllist.add("ccc");
		alllist.add("ddd");
		alllist.add("ddd");
		/*System.out.println(alllist.stream().filter(a -> a.equals("2c")).collect(Collectors.toList()));
		System.out.println("init size = " + alllist.size());
		// distinct
		alllist.stream().distinct();
		System.out.println("after distinc size = " + alllist.stream().distinct().count());
		*//**for (String s: alllist) {
			System.out.println(s);
		}*//*
		// map
		//对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。
		// 这个方法有三个对于原始类型的变种方法，分别是：mapToInt，mapToLong和mapToDouble。这三个方法也比较好理解，
		// 比如mapToInt就是把原始Stream转换成一个新的Stream，这个新生成的Stream中的元素都是int类型。
		// 之所以会有这样三个变种方法，可以免除自动装箱/拆箱的额外消耗

		List<String> disList = alllist.stream().distinct().map(String :: toString).collect(Collectors.toList());
		disList.forEach( a -> System.out.println(a));

		//filter
		//  对于Stream中包含的元素使用给定的过滤函数进行过滤操作，新生成的Stream只包含符合条件的元素
*/
		List<String> filList = alllist.stream().filter(abc -> abc.indexOf("c") > -1).collect(Collectors.toList());
		System.out.println("after filter .......");
		filList.forEach(a -> System.out.println(a));

		// peek
		// 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），
		// 新Stream每个元素被消费的时候都会执行给定的消费函数
		System.out.println("after peek .......");
		List<String> peekList = alllist.stream().peek(abc -> abc.substring(1)).collect(Collectors.toList());
		peekList.forEach(a -> System.out.println(a));

		// groupingBy
		Map p = alllist.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(p);

		Ent ent = new Ent();
		ent.setName("zhangsan");
		ent.setValue("98");
		Ent ent2 = new Ent();
		ent2.setName("zv");
		ent2.setValue("3");
		Ent ent3 = new Ent();
		ent3.setName("GG");
		ent3.setValue("323");
		Ent ent4 = new Ent();
		ent4.setName("zhangsan");
		ent4.setValue("42");
		List<Ent> list = new ArrayList<Ent>();
		list.add(ent);
		list.add(ent2);
		list.add(ent3);
		list.add(ent4);
		Map<String,List<Ent>> pp = list.stream().collect(Collectors.groupingBy(Ent :: getName));
		System.out.println(pp);
		Set<Map.Entry<String,List<Ent>>> set = pp.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext()){
			Map.Entry<String,List<Ent>> entry =(Map.Entry<String, List<Ent>>) it.next();
			System.out.println("key==="+entry.getKey());
			List<Ent> list1 = entry.getValue();
			list1.forEach(ac -> System.out.println(ac.getValue() + " ---" + ac.getName()));
		}
	}

	private static class Ent{

		String name ;
		String value;
		public Ent(){}
		public void setName(String name){
			this.name = name;
		}
		public String getName(){
			return this.name;
		}
		public void setValue(String value){
			this.value = value;
		}
		public String getValue(){
			return this.value;
		}
	}
}