package studiii.zlsj_test.basics.dataType;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月27日 上午11:13:27
 * Modified By:
 */
public class DoubleFloatEqual {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double d = new Double(1.2);
		Double d1 = new Double(1.2d);
		System.out.println(d.equals(d1));
		System.out.println(d==d1);
		Float f = new Float(1.2f);
		Float f1 = new Float(1.2);
		System.out.println(f==f1);
		System.out.println(f.equals(f1));
		
		/**
		 * Double 重写equals方法 ，如果参数不是Double对象，那么就不用比较值了，直接false
		 *  return (obj instanceof Double)
               && (doubleToLongBits(((Double)obj).value) ==
                      doubleToLongBits(value));
		 */
		System.out.println(d.equals(f1));
	}

}
