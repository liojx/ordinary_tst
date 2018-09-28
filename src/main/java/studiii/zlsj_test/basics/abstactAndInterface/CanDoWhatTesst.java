package studiii.zlsj_test.basics.abstactAndInterface;

import java.lang.reflect.Field;

/**
 * @author liaosijun
 * @since 2018年9月27日 下午5:23:18
 */
public class CanDoWhatTesst {
	
	public static void main(String[] args) {
		Class clazz = Teb.class;
		Field[] fields = clazz.getDeclaredFields();
		for(Field f : fields) {
			System.out.println(f.getModifiers() + "  " + f.toString());
		}
	}
	
	class Teb implements InterFaceCanDoWhat{

		/* (non-Javadoc)
		 * @see studiii.zlsj_test.basics.abstactAndInterface.InterFaceCanDoWhat#method1()
		 */
		@Override
		public void method1() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
