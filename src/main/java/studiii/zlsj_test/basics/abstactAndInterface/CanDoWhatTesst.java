package studiii.zlsj_test.basics.abstactAndInterface;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import studiii.zlsj_test.basics.ModifierUtil;

/**
 * @author liaosijun
 * @since 2018年9月27日 下午5:23:18
 */
public class CanDoWhatTesst {
	
	public static void main(String[] args) {
		Class<InterFaceCanDoWhat> clazzB = InterFaceCanDoWhat.class;
		Field[] fields = clazzB.getDeclaredFields();
		for(Field f : fields) {
			System.out.println(f.getModifiers() + "   " + f.getName());
			// 接口里面声明了三个变量，结果修饰符都是 public static final
			System.out.println(ModifierUtil.getModifier(f.getModifiers()));
		}
		
		Method[] ms = clazzB.getDeclaredMethods();
		for(Method m : ms) {
			System.out.println(ModifierUtil.getModifier(m.getModifiers()) + m.getName());
		}
		// InterFaceCanDoWhat iter = new InterFaceCanDoWhat(); // 接口不能实例
//		InterFaceCanDoWhat iter = new InterFaceCanDoWhat() {
//
//			@Override
//			public void method1() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		}; // 接口不能实例 ,但可以内部类实现
//		AbstractClass ac = new AbstractClass() {
//
//			@Override
//			void method2() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		};
	}
	

}
