package studiii.zlsj_test.cdBankPeriod.tools;

public class Selfarr {
	private Object[] objects = null;
	
	public Selfarr(){
		
	}
	
	public int add(Object obj) throws Exception{
		int count = 0;
		this.objects[this.objects.length]=obj;
		return count;
	}
	
	public static void main(String[] args) {
		Selfarr arr = new Selfarr();
		try {
			arr.add("ribenren");
			System.out.println(arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
