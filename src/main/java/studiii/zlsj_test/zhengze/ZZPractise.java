package studiii.zlsj_test.zhengze;


import studiii.zlsj_test.zhengze.src.Matcher;
import studiii.zlsj_test.zhengze.src.Pattern;

/**
 * @Description: 正则表达是测试
 * @Author: liaosijun
 * @Time: 2019/6/28 10:18
 */
public class ZZPractise {
	/**
	 *
	 *  1 ---------  \d,数字匹配,等效于[0,9]
	 *  2 ---------  [xyz] ,中括号表示匹配括号里面的任意字符，比如[abc]匹配 plain 中的a
	 *  3 ---------  [a-z],[0-9], 表示匹配的范围，前者表示匹配a-z中任意一个小写字符，后者表示匹配0-9中任意一个数字
	 *  4 ---------  \D ,大D和小d刚好相反，匹配非数字
	 *
	 *  5 ---------  *, 0到多次，相当于{0,},例如zo*,匹配z和zo和zoo
	 *  6 ---------  +, 1到多次，相当于{1,},例如zo+,匹配zo和zoo，但不匹配z
	 *  7 ---------  ?, 0或者1次，相当于{0,1}, 例如do(es)? ,可以匹配do和dose
	 *  8 ---------  {n},正好匹配n次，"o{2}"与"Bob"中的"o"不匹配，但与"food"中的两个"o"匹配
	 *  9 ---------  {n,}，n 是非负整数。至少匹配 n 次。例如，"o{2,}"不匹配"Bob"中的"o"，而匹配"foooood"中的所有 o。
	 *               "o{1,}"等效于"o+"。"o{0,}"等效于"o*"。
	 *  10 --------  {n,m}，m 和 n 是非负整数，其中 n <= m。匹配至少 n 次，至多 m 次。例如，"o{1,3}"匹配"fooooood"中的头三个 o。
	 *               'o{0,1}' 等效于 'o?'。注意：您不能将空格插入逗号和数字之间。
	 *  11 --------  ?, 这个问号是跟在限定符后面的问号，当此字符紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，
	 *               匹配模式是"非贪心的"。"非贪心的"模式匹配搜索到的、尽可能短的字符串，而默认的"贪心的"模式匹配搜索到的、
	 *               尽可能长的字符串。例如，在字符串"oooo"中，"o+?"只匹配单个"o"，而"o+"匹配所有"o"。
	 *  12 --------  ^, 匹配输入字符串开始的位置。如果设置了 RegExp 对象的 Multiline 属性，^ 还会与"\n"或"\r"之后的位置匹配。
	 *  13 --------  [^xyz],反向字符集。匹配未包含的任何字符。例如，"[^abc]"匹配"plain"中"p"，"l"，"i"，"n"。
	 *  14 --------  匹配除"\r\n"之外的任何单个字符。若要匹配包括"\r\n"在内的任意字符，请使用诸如"[\s\S]"之类的模式
	 *
	 *
	 *  15 -------- 匹配 pattern 并捕获该匹配的子表达式。可以使用 $0…$9 属性从结果"匹配"集合中检索捕获的匹配。
	 *              若要匹配括号字符 ( )，请使用"\("或者"\)"。
	 *
	 *  16 -------- \w，匹配任何字类字符，包括下划线。与"[A-Za-z0-9_]"等效
	 *  17 -------- \W,与任何非单词字符匹配。与"[^A-Za-z0-9_]"等效。
	 *
	 *  18 -------- \b,匹配一个字边界，即字与空格间的位置。例如，"er\b"匹配"never"中的"er"，但不匹配"verb"中的"er"。
	 *  19 -------- \B,非字边界匹配。"er\B"匹配"verb"中的"er"，但不匹配"never"中的"er"
	 *
	 *  20 -------- . ,匹配除"\r\n"之外的任何单个字符。若要匹配包括"\r\n"在内的任意字符，请使用诸如"[\s\S]"之类的模式
	 */
	public static void main(String[] args) {
		split1();
	}

	static void split1() {
		String srcStr = "(3002,455999)";
		/*String[] arr = srcStr.split(",");
		for (String s : arr) {
			System.out.println("------>>> " + s);
		}*/

		String pattern = "([\\[|\\(])(\\d+)(.)(\\d+)([\\)|\\]])";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(srcStr);
		System.out.println("groupcount ==== " + (m.groupCount() + 1) );
		int i = 0;
		while (m.find()) {
			System.out.println("--------> " + m.group(1));
			System.out.println("--------> " + m.group(2));
			System.out.println("--------> " + m.group(3));
			System.out.println("--------> " + m.group(4));
			System.out.println("--------> " + m.group(5));
		}


		/*String regex = "\\$\\{([\\w]+?)\\}";
		Pattern ptt = Pattern.compile(regex);
		String input = "${name}-babalala-${age}-${address}";

		Matcher matcher = ptt.matcher(input);
		while (matcher.find()) {
			System.out.println("groupcount ==== " + m.groupCount());
			System.out.println(matcher.group(0) + ", pos: " + matcher.start());
			System.out.println(matcher.group(1) + ", pos: " + matcher.start(1));
		}*/

		/*String s1 = "[^{}]";
		String a = "ali";
		String b = "baidu";
		String c = "chidao";
		String d = "dd";
		Pattern p = Pattern.compile(s1);

		Matcher m = p.matcher(d);
		System.out.println("m.start() = " + m.start());
		System.out.println("m.end() = " + m.end());
		System.out.println("m.group() = " + m.group());
		// matches  方法必须通过m.start() end() 和group()方法
		System.out.println(m.matches());*/
	}
}