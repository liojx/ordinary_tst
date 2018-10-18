package studiii.zlsj_test.cdBankPeriod.tools;

import java.util.ArrayList;

public class TestSetObject {
	
	class Ping{
		private String name  ="Sunny";
		private int age = 20;
		TT tt = new TT();
		TT tt2 = new TT();
		private ArrayList<TT> tarr = new ArrayList<TT>();
		private ArrayList<String> tarr2 = new ArrayList<String>();
		public void tx(){
			tt.setGg(1);
			tt.setHut("2");
			tt.setHutai("3");
			tt2.setGg(12);
			tt2.setHut("22");
			tt2.setHutai("33");
			tarr.add(tt);
			tarr.add(tt2);
			tarr2.add("0");
			tarr2.add("00");
			tarr2.add("000");
			
		}
	}
	
	class TT{
		private String hutai ="hello";
		private int gg = 20;
		private String hut ="w";
		public String getHutai() {
			return hutai;
		}
		public void setHutai(String hutai) {
			this.hutai = hutai;
		}
		public int getGg() {
			return gg;
		}
		public void setGg(int gg) {
			this.gg = gg;
		}
		public String getHut() {
			return hut;
		}
		public void setHut(String hut) {
			this.hut = hut;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return +this.gg + "   "+this.hut +"   "+this.hutai ;
		}
	}
	public void uu(){
		TestSetObject o = new TestSetObject();
		TT juju = new TT();
		juju.setGg(50);
		juju.setHut("60");
		juju.setHutai("70");
		Ping p = new Ping();
		p.tx();
		for(TT t : p.tarr){
			t = juju;
			break;
		};
		for(String s : p.tarr2){
			s = "88888";
			break;
		};
		
		System.out.println(p.tarr2.get(0));
	}
	public static void main(String[] args) {
		TestSetObject o = new TestSetObject();
		o.uu();
	}
}
