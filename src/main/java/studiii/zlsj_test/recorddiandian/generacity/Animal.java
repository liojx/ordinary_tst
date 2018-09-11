package studiii.zlsj_test.recorddiandian.generacity;

/**
 * Author: liaosijun
 * Description:动物
 * Date: Created in 2018/8/21 15:36
 * Modified By:
 */
public class Animal extends Biology {

    private String name ;

    public Animal(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
