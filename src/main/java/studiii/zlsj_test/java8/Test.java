package studiii.zlsj_test.java8;

import java.util.ArrayList;

/**
 * Author: liaosijun
 * Description:
 * Date: Created in 2018/8/23 18:31
 * Modified By:
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<String>();
        for(int i = 0; i <= 10;i++){
            arr.add(i+"");
        }
        arr.forEach(s -> {
            System.out.println(s);
        });

        arr.forEach(Test :: pri);
    }
    static void pri(String s){
        System.out.println(s);
    }
}
