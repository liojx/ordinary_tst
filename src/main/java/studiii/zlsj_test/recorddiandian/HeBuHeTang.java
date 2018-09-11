package studiii.zlsj_test.recorddiandian;

import java.util.EnumSet;

/**
 * Author: liaosijun
 * Description:
 * Date: Created in 2018/8/21 14:46
 * Modified By:
 */
public enum HeBuHeTang {
    MUTOUREN("xx",1),MUXIFENG("me",2),FEN("moth",3),LIAOMUJIANG("fa",4);

    private final String name;

    private final int v;

    private HeBuHeTang(String name,int v){
        this.name = name;
        this.v = v;
    }

    public static void main(String[] args) {
        System.out.println(HeBuHeTang.FEN.name);

        EnumSet<HeBuHeTang> es = EnumSet.noneOf(HeBuHeTang.class);
        es.add(HeBuHeTang.LIAOMUJIANG);
        es.add(HeBuHeTang.MUXIFENG);
        es.add(HeBuHeTang.LIAOMUJIANG);
        es.add(HeBuHeTang.MUTOUREN);
        es.add(HeBuHeTang.FEN);
        es.add(HeBuHeTang.FEN);
        es.add(HeBuHeTang.MUTOUREN);

        for (HeBuHeTang hbht: es) {
            System.out.println(hbht.name);
        }
    }
}
