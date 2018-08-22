package studiii.zlsj_test.recorddiandian.generacity;

/**
 * Author: liaosijun
 * Description:
 * Date: Created in 2018/8/21 17:11
 * Modified By:
 */
public class FlyingAnimal<T>  {

    private T t ;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void showType(){
        System.out.println("T的实际类型是：" + t.getClass().getName());
    }

    public FlyingAnimal(String name) {
        this.name = name;
    }

    public FlyingAnimal(String name,T t) {
        this.t = t;
    }

    @Override
    public String toString(){
        return "this.name = "+this.name;
    }
}
