package studiii.zlsj_test.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import studiii.zlsj_test.pojo.DBPO;

/**
 * Author: liaosijun
 * Description:
 * Date: Created in 2018/8/23 18:31
 * Modified By:
 */
public class Test {
    public static void main(String[] args) {
//        ArrayList<String> arr = new ArrayList<String>();
//        for(int i = 0; i <= 10;i++){
//            arr.add(i+"");
//        }
//        arr.forEach(s -> {
//            System.out.println(s);
//        });
//
//        arr.forEach(Test :: pri);
        abc();
    }
    static void pri(String s){
        System.out.println(s);
    }
    
    static void abc() {
    	List<DBPO> userProps = new ArrayList<DBPO>();
    	DBPO po1 = new DBPO();
    	po1.setUrl("/a");
    	po1.setPwd("123");
    	po1.setUserName("ls");
    	DBPO po2 = new DBPO();
    	po2.setUrl("/b");
    	po2.setPwd("123");
    	po2.setUserName("ls");
    	DBPO po3 = new DBPO();
    	po3.setUrl("/c");
    	po3.setPwd("123");
    	po3.setUserName("ls");
    	
    	userProps.add(po1);
    	userProps.add(po2);
    	userProps.add(po3);
    	
    	List<String> stf = userProps.stream().map(st ->st.getUrl()).collect(Collectors.toList());
    	stf.forEach(Test :: pri);
    	
    }
}
