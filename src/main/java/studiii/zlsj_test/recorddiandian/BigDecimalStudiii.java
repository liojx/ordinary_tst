package studiii.zlsj_test.recorddiandian;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.math.BigDecimal;

/**
 * Author: liaosijun
 * Description:
 * Date: Created in 2018/8/21 10:27
 * Modified By:
 */
public class BigDecimalStudiii {

    public static void main(String[] args) {
        System.out.println(0.2d+0.2f);
        System.out.println(1.92+8.43);
        System.out.println(Short.MAX_VALUE+10);
        System.out.println();
        System.out.println(Short.MIN_VALUE);
        System.out.println(Short.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Float.MIN_VALUE);
        System.out.println(Float.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println(Character.MIN_VALUE);
        System.out.println(Character.MAX_VALUE);
        System.out.println(Byte.MIN_VALUE);
        System.out.println(Byte.MAX_VALUE);

        System.out.println(new BigDecimal("1.2"));
    }
}
