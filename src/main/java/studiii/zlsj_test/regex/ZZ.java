package studiii.zlsj_test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liaosijun
 * @since 2019年1月4日 下午5:15:20
 */
public class ZZ {
	
	public static void main(String[] args) {
		
		// . 任意字符 除开 \r\n
		System.out.println("------------.------------");
		String a = "dot11";
		String reg = ".";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(a);
		System.out.println(m.find());
		while(m.find()) {
			System.out.println(m.start());
		}
		a = "\n";
		 p = Pattern.compile(reg);
		 m = p.matcher(a);
		System.out.println(m.find());
		while(m.find()) {
			System.out.println(m.start());
		}
		System.out.println("------------.------------");
		System.out.println();
		
		// ^ 字符串开始的位置 ，Multiline 会匹配\n或者\r后面的字符
		System.out.println("------------^------------");
		String  b = "   23ddsbbccsdsdcckdc  \n x ";
		String reg1 = "^";
		p = Pattern.compile(reg1);
		m = p.matcher(b);
		while(m.find()) {
			System.out.println(m.start());
		}
		p = Pattern.compile(reg1,Pattern.MULTILINE);
		m = p.matcher(b);
		while(m.find()) {
			System.out.println("加了MuLtiLine 属性" +  m.start());
		}
		System.out.println("------------^------------");
		System.out.println();
		
		// $ 字符结束位置，Multiline 会匹配 \n 或者\r 之前的的位置
		System.out.println("------------$------------");
		String  c = "  \n 23ddsbbccsdsdcckdc  \n x ";
		String reg2 = "$";
		p = Pattern.compile(reg2);
		m = p.matcher(c);
		while(m.find()) {
			System.out.println(m.start());
		}
		p = Pattern.compile(reg2,Pattern.MULTILINE);
		m = p.matcher(c);
		while(m.find()) {
			System.out.println("加了MuLtiLine 属性" +  m.start());
		}
		System.out.println("------------$------------");
		
		System.out.println();
		
		// * 0到多次
		System.out.println("------------*------------");
		String d = "frooooog";
		String reg3 = "b*";
		p = Pattern.compile(reg3);
		m = p.matcher(d);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
		}
		System.out.println("------------*------------");
		System.out.println();
		
		//+ 1到多次
		System.out.println("------------+------------");
		String reg4 = "b+";
		p = Pattern.compile(reg4);
		m = p.matcher(d);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
		}
		d = "dsdbb";
		p = Pattern.compile(reg4);
		m = p.matcher(d);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
		}
		System.out.println("------------+------------");
		
		System.out.println();
		
		// ? 0或1次
		System.out.println("------------?------------");
		String e = "0";
		String reg5 = "l?";
		p = Pattern.compile(reg5);
		m = p.matcher(e);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
		}
		e = "asdfaliaosi  \\n\t";
		p = Pattern.compile(reg5);
		m = p.matcher(e);
		while(m.find()) {
			System.out.println("有" + m.start() + "-" + m.end());
		}
		System.out.println("------------?------------");
		
		System.out.println();
		//{n}, 正好n次
		System.out.println("------------{n}------------");
		String f = "liaosijun";
		String reg6 = "i{1}";
		p = Pattern.compile(reg6);
		m = p.matcher(f);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
		}
		f = "xxljjaosiiiijjn";
		reg6 = "j{2}";
		p = Pattern.compile(reg6);
		m = p.matcher(f);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
		}
		
		System.out.println();
		
		//{n,} 至少n次
		System.out.println("------------{n,}------------");
		String g = "liaosijuniijsiijl";
		String reg7 = "i{2}";
		p = Pattern.compile(reg7);
		m = p.matcher(g);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
			System.out.println(m.group());
			System.out.println(m.group(0));
			System.out.println(m.groupCount());
		}
		System.out.println("------------{n,}------------");
		System.out.println();

		//{n,m} 至少n次，至多m次
		System.out.println("------------{n,m}------------");
		String h = "liaosiiiijuniiixxd";
		String reg8 = "i{2,3}";
		p = Pattern.compile(reg8);
		m = p.matcher(h);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
		}
		System.out.println("------------{n,m}------------");
		System.out.println();
		
		/**
		 *	?跟在限定符后,标识不是贪心搜索，限定符包括*、+、?、{n}、{n,}、{n,m}
		 *	? 跟在* 后面好像没变化耶
		 *	? 跟在+ 后面，就是匹配到一个时就又重新算起，重新去匹配，如果没加?的+ 是匹配连续的1-多个
		 *	? 跟在 ? 后面也挺有意思，加入liao, 用i? 那么l也匹配 ,表示出现0次i,group()值是空白，i 匹配，表示出现1次i,group()值就是i , a也匹配，o也匹配
		 *	如果是??的话，他也一样l i a o都能够挨个匹配，但是会发现，匹配i时，group不再是i，而是空白，说明这个非贪心的?，再匹配到0次i时，就又匹配下一个去了，所以这里group输出是空白
		 *	? 跟在{n}后，没什么变化，因为这是恰好n次
		 *	? 跟在{n,}后，效果就很明显，o{2,} 会匹配gooodoooovooco中的g后面的3个o,d后面的4个o和v后面的两个o,如果是o{2,}?,那么就匹配g后面的2个o,d后面的前面2个o,d后面的后面2个o，v后面的2个o
		 *	? 跟在{n,m}后，比较清晰，他就相当于{n}这个了，只匹配第一个至少的情况，至多的就不考虑了
		 *	
		 */
		System.out.println("------------?跟在限定符后------------");
		String i = "liaosiiiijuniiixxd";
		String reg9 = "i*?";
		p = Pattern.compile(reg9);
		m = p.matcher(i);
		while(m.find()) {
			System.out.println("[*]" + m.start() + "-" + m.end());
		}
		reg9 = "i+?";
		p = Pattern.compile(reg9);
		m = p.matcher(i);
		while(m.find()) {
			System.out.println("[+?]" + m.start() + "-" + m.end());
			System.out.println("[+?]"  + m.group());
			System.out.println("[+?]"  + m.groupCount());
		}
		
		reg9 = "i+";
		p = Pattern.compile(reg9);
		m = p.matcher(i);
		while(m.find()) {
			System.out.println("[+]" + m.start() + "-" + m.end());
			System.out.println("[+]"  + m.group());
			System.out.println("[+]"  + m.groupCount());
		}
		
		reg9 = "i?";
		p = Pattern.compile(reg9);
		m = p.matcher(i);
		while(m.find()) {
			System.out.println("[?]" + m.start() + "-" + m.end());
			System.out.println("[?]"  + m.group());
			System.out.println("[?]"  + m.groupCount());
		}
		reg9 = "i??";
		p = Pattern.compile(reg9);
		m = p.matcher(i);
		while(m.find()) {
			System.out.println("[??]" + m.start() + "-" + m.end());
			System.out.println("[??]"  + m.group());
			System.out.println("[??]"  + m.groupCount());
		}
		
		reg9 = "i{2}?";
		p = Pattern.compile(reg9);
		m = p.matcher(i);
		while(m.find()) {
			System.out.println("[{n}?]" + m.start() + "-" + m.end());
			System.out.println("[{n}?]"  + m.group());
			System.out.println("[{n}?]"  + m.groupCount());
		}
		
		reg9 = "i{2,}";
		p = Pattern.compile(reg9);
		m = p.matcher(i);
		while(m.find()) {
			System.out.println("[{n,}]" + m.start() + "-" + m.end());
			System.out.println("[{n,}]"  + m.group());
			System.out.println("[{n,}]"  + m.groupCount());
		}
		reg9 = "i{2,}?";
		p = Pattern.compile(reg9);
		m = p.matcher(i);
		while(m.find()) {
			System.out.println("[{n,}?]" + m.start() + "-" + m.end());
			System.out.println("[{n,}?]"  + m.group());
			System.out.println("[{n,}?]"  + m.groupCount());
		}
		
		reg9 = "i{1,9}?";
		p = Pattern.compile(reg9);
		m = p.matcher(i);
		while(m.find()) {
			System.out.println("[{n,m}?]" + m.start() + "-" + m.end());
			System.out.println("[{n,m}?]"  + m.group());
			System.out.println("[{n,m}?]"  + m.groupCount());
		}
		System.out.println("------------?跟在限定符后------------");
		
		System.out.println();
		
		// 匹配 pattern 并捕获该匹配的子表达式。可以使用 $0…$9 属性从结果"匹配"集合中检索捕获的匹配。若要匹配括号字符 ( )，请使用"\("或者"\)"
		System.out.println("------------(pattern)------------");
		String j = "zhangtian356sld";
		String reg10 = "(\\d)";
		p = Pattern.compile(reg10);
		m = p.matcher(j);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
			System.out.println("groupCount=" + m.groupCount());
			System.out.println("group(0)=" + m.group(0));
			System.out.println("group(1)=" + m.group(1));
		}
		System.out.println("------------(pattern)------------");
		
		System.out.println();
		// 匹配 pattern 但不捕获该匹配的子表达式，即它是一个非捕获匹配，不存储供以后使用的匹配。这对于用"or"字符 (|) 组合模式部件的情况很有用。
		// 例如，'industr(?:y|ies) 是比 'industry|industries' 更经济的表达式。
		System.out.println("------------(?:pattern)------------");
		String k = "zhangtian356sld";
		String reg11 = "(?:\\d)";
		p = Pattern.compile(reg11);
		m = p.matcher(k);
		while(m.find()) {
			System.out.println(m.start() + "-" + m.end());
			System.out.println("groupCount=" + m.groupCount());
			System.out.println("group(0)=" + m.group(0));
		}
		System.out.println("------------(?:pattern)------------");
	}
}
