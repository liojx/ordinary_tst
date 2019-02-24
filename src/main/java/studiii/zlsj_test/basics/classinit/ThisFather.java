package studiii.zlsj_test.basics.classinit;

import java.lang.reflect.Constructor;

/**
 * @author liaosijun
 * @since 2018年9月27日 下午2:09:53
 */
public class ThisFather {
	
	public ThisFather(Integer bas) {
		
	}
	
	public ThisFather(String str) {
		
	}
	
	/**
	 * 显示的声明出来，它的子类如果没有写构造是不报错的，如果不写出来，上面又有无参构造，子类要报错：父类无默认构造，必须自定义明确的构造
	 */
	public ThisFather() {
		
	}
	/**
	 *  PUBLIC: 1
		PRIVATE: 2
		PROTECTED: 4
		STATIC: 8
		FINAL: 16
		SYNCHRONIZED: 32
		VOLATILE: 64
		TRANSIENT: 128
		NATIVE: 256
		INTERFACE: 512
		ABSTRACT: 1024
		STRICT: 2048	
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 如果显示的声明了有参构造，无参构造就失效了，如果要无参构造，需要手动的写出来
		 * 如果上面一个都不写，也是有个默认构造的
		 */
		Class clazz = ThisFather.class;
		Constructor[] cons = clazz.getConstructors();
		for(Constructor c : cons) {
//			System.out.println(c.getName() + "    " + c.getModifiers() + "   " + c.getParameters()[0].getType());
			System.out.println(c.getName() + "    " + c.getModifiers()  );
		}
	}
}
