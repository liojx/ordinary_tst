package studiii.zlsj_test.recorddiandian.generacity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: liaosijun
 * Description:测试泛型指派范围 ? T 点 和 面的概念
 * Date: Created in 2018/8/21 15:40
 * Modified By:
 */
public class Test {
    public static void main(String[] args) {

        /**
         * 第一部分，主要看点和面的概念，当尖括号里面是确定的某一类型，那么就是点的概念
         * 如果尖括号里面是带有问号的，说明这个泛型是一个范围，这就是面的概念
         * 小范围的面可以赋值给包含小范围的大范围
         * 大范围是不能够赋值给小范围的
         * 范围也不能赋值给点的
         */
        List<?> listAll = new ArrayList<Tiger>();  // ? 代表全部，List<?> 和list的范围一样

        /**
         * 第四个会报错，由于List<? super Animal> 的范围是Animal 或Animal的符类，而Reptile是Animal的子类，故编译期就报错出来了
         */
        List<? super Animal> listSuperAnimal1 = new ArrayList<Animal>();
        List<? super Animal> listSuperAnimal2 = new ArrayList<Object>();
        List<? super Animal> listSuperAnimal3 = new ArrayList<Biology>();
//      List<? super Animal> listSuperAnimal4= new ArrayList<Reptile>(); 会出错

        /**
         * 第三个报错，由于List<? extends Reptile> 的范围是Reptile 爬行动物 及 Reptitle 的子类，你把生物类装进去，肯定是报错的
         */
        List<? extends Reptile> listExtendsReptile1 = new ArrayList<Reptile>();
        List<? extends Reptile> listExtendsReptile2 = new ArrayList<Tortoise>();
//        List<? extends Reptile> listExtendsReptile3 = new ArrayList<Biology>(); 出错

        //如果类型不一样，点与点之间的赋值是错误的，别人规定只能装Biology 生物了，你却要装一个动物
//        List<Biology> listBiology = new ArrayList<Animal>();

        //可以把点赋值给范围 ，=右边一个点，只装Biology的list, 左边是一个范围，代表Animal及Animal的父类范围都可以
        listSuperAnimal2 = new ArrayList<Biology>();

        // 小范围赋值给大范围是可以的， =右边是Animal及Animal的父类，左边是Reptile 及Reptle的父类。
        List<? super Reptile> listSuperReptile = listSuperAnimal1;

        //下面两种赋值都是错误的，点赋值给面是不对的
//        List<Animal> listAnimal = listSuperAnimal2;
//        List<? extends Animal> listExtendsAnimal = null;
//        List<Animal> listAnimal = listExtendsAnimal;


        /**
         * 第二部分，讲解T和?的区别，?是表示一个占位符，范围类型的。而T是表示确定的某一类型，请看下面的例子
         */
        //我们的FlyingAnimal<T> extends Animal
        Animal dog = new Animal("dog");
        Animal cat = new Animal("cat");
        Tiger tiger = new Tiger("tiger");
        List  list = new ArrayList();
        list.add(dog);
        list.add(cat);
        list.add(tiger);
        Test test = new Test();

        /**
         * 下面我加了两个方法，一个是list<T> 一个是传入list<?>,以为前者就只能传入包含一种类型的list ,后者可以传入包含多种类型的list
         * 事实告诉我，错了
         */
        test.print2(list);
        test.print1(list);

        /**
         * 后我又加了一个类 FlyingAnimal<T>,好我来验证一下
         */
        FlyingAnimal fa = new FlyingAnimal("鹰");
        System.out.println(fa);
        FlyingAnimal<String> fa2 = new FlyingAnimal<String>("秃鹫");
        System.out.println(fa2);
        /**
         * 以为不加泛型new的时候会报错，结果也没有得到预期
         */

        /**
         * 继续验证，我在FlyingAnimal<T>中加入了一个类变量T t,同时增加T的getter和setter方法，同时打印构造FlyingAnimal<T>
         *  时的类型t.getClass().getName，再试试
         */
        FlyingAnimal fa3 = new FlyingAnimal<Animal>("飞熊光头强",new Animal("dog"));
        fa3.showType();
        FlyingAnimal fa4 = new FlyingAnimal<Biology>("格桑花",new Animal("猪"));
        fa4.showType();
        FlyingAnimal fa5 = new FlyingAnimal<Biology>("格桑花",new Biology());
        fa5.showType();
        /**
         * OK，到这里，大概知道这个<T>有什么作用了
         */
    }

    <T> void print1(List<T> list){
        for (Object o : list){
            System.out.println(o.toString());
        }
    }

    void print2(List<?> list){
        for (Object o : list){
//            System.out.println("====");
            System.out.println(o);
        }
    }
}
