package studiii.zlsj_test.basics.interesting.twoColorBall;

/**
 * @author liaosijun
 * @since 2018年10月15日 下午2:34:04
 */
public class Ball {
	
	private int r1;
	private int r2;
	private int r3;
	private int r4;
	private int r5;
	private int r6;
	private int blue;
	private int rownum;
	private int cnt;
	/**
	 * @return the r1
	 */
	public int getR1() {
		return r1;
	}
	/**
	 * @param r1 the r1 to set
	 */
	public void setR1(int r1) {
		this.r1 = r1;
	}
	/**
	 * @return the r2
	 */
	public int getR2() {
		return r2;
	}
	/**
	 * @param r2 the r2 to set
	 */
	public void setR2(int r2) {
		this.r2 = r2;
	}
	/**
	 * @return the r3
	 */
	public int getR3() {
		return r3;
	}
	/**
	 * @param r3 the r3 to set
	 */
	public void setR3(int r3) {
		this.r3 = r3;
	}
	/**
	 * @return the r4
	 */
	public int getR4() {
		return r4;
	}
	/**
	 * @param r4 the r4 to set
	 */
	public void setR4(int r4) {
		this.r4 = r4;
	}
	/**
	 * @return the r5
	 */
	public int getR5() {
		return r5;
	}
	/**
	 * @param r5 the r5 to set
	 */
	public void setR5(int r5) {
		this.r5 = r5;
	}
	/**
	 * @return the r6
	 */
	public int getR6() {
		return r6;
	}
	/**
	 * @param r6 the r6 to set
	 */
	public void setR6(int r6) {
		this.r6 = r6;
	}
	/**
	 * @return the blue
	 */
	public int getBlue() {
		return blue;
	}
	/**
	 * @param blue the blue to set
	 */
	public void setBlue(int blue) {
		this.blue = blue;
	}
	/**
	 * @return the rownum
	 */
	public int getRownum() {
		return rownum;
	}
	/**
	 * @param rownum the rownum to set
	 */
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	/**
	 * @return the cnt
	 */
	public int getCnt() {
		return cnt;
	}
	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.r1 + " " + this.r2 + " " + this.r3 + " " + this.r4 + " " + this.r5 + " " + this.r6 + " | " + this.blue +" 出现次数："+this.cnt;
	}
}
