package studiii.zlsj_test.basics.interesting.twoColorBall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import studiii.zlsj_test.util.DBUtil;

/**
 * @author liaosijun
 * @since 2018年10月15日 上午11:24:50
 */
public class SaveInDataBase {
	
	static void towColorBall(int up,int down) {
		 //边界条件
		 //边界条件不满足，继续递归
		 //边界条件满足，跳出递归
		 
		 // 这里边界条件： 当你的填充值fill == down 数值时跳出递归
		 // 
//		 int[] a = new int[down-up+fill];
//		 int idx =0;
//		 if(fill <= up) {
//			 for(int i=fill ;i<=(down-up+fill);i++) {
//				 a[idx++] = i;
//			 }
//		 }
		Map<Integer,List> map = new HashMap<Integer,List>();
		 for(int ac = 1;ac<=up;ac++) {
			 List<Integer> arr = new ArrayList<Integer>();
			 int indexOf = ac;
			 int cc = indexOf;
			 do {
				 arr.add(cc);
				 ++cc;
			 }while(cc <= (down-up+indexOf));// 例如C7选3，那么第1 位可以排 1到7-3+1 = 5，第2位 能排2到7-3+2=6，第3位只能排3到7-3+3=7
			 map.put(ac, arr);
		 }
		 for(int i =0;i<map.size();i++) {
			 List<Integer> l = map.get(i+1);
			 for(Integer j : l) {
//				 System.out.println(j);
			 }
		 }
		 
		 
		 /**笨办法选6位**/
		 List<Integer> l1 = map.get(1);		 
		 List<Integer> l2 = map.get(2);		 
		 List<Integer> l3 = map.get(3);	
		 List<Integer> l4 = map.get(4);	
		 List<Integer> l5 = map.get(5);	
		 List<Integer> l6 = map.get(6);	
		 List<int[]> all = new ArrayList<int[]>();
		 int idx1 = 0;
		 int idx2 = 0;
		 int idx3 = 0;
		 int idx4 = 0;
		 int idx5 = 0;
		 int idx6 = 0;
		 int zx = 0;
		 while(zx<l1.size()) {
			 idx1 = l1.get(zx);
			 int bx = 0;
			 while(bx<l2.size()) {
				 idx2 = l2.get(bx);
				 if(idx2 <= idx1) {
					 bx++;
					 continue;
				 }
				 int dx = 0;
				 while(dx<l3.size()) {
					 idx3 = l3.get(dx);
					 if(idx3 <= idx2 || idx3 <= idx1) {
						 dx++;
						 continue;
					 }
					int ex = 0;
					while(ex<l4.size()) {
						idx4 = l4.get(ex);
						if(idx4 <= idx3 || idx4 <= idx2 || idx4<=idx1) {
							ex++;
							continue;
						}
						int fx =0;
						while(fx<l5.size()) {
							idx5 = l5.get(fx);
							if(idx5 <= idx4  ||idx5 <= idx3 || idx5 <= idx2 || idx5<=idx1) {
								fx++;
								continue;
							}
							int gx = 0;
							while(gx<l6.size()) {
								idx6 = l6.get(gx);
								if(idx6 <=idx5 || idx5 <= idx4  ||idx5 <= idx3 || idx5 <= idx2 || idx5<=idx1) {
									gx ++;
									continue;
								}
								 int[] abc = new int[6];
								 abc[0] = idx1;
								 abc[1] = idx2;
								 abc[2] = idx3;
								 abc[3] = idx4;
								 abc[4] = idx5;
								 abc[5] = idx6;
								 all.add(abc);
								gx++;
							}
							fx++;
						}
						ex++;
					}
					 dx++;
				 }
				 bx++;
			 }
			 zx++;
		 }
//		 for(int[] aaa:all) {
//			 Logger.log(aaa[0] +"-"+aaa[1]+"-"+aaa[2] +"-"+aaa[3]+"-"+aaa[4]+"-"+aaa[5]);
////			 System.out.println(aaa[0] +"-"+aaa[1]+"-"+aaa[2] +"-"+aaa[3]+"-"+aaa[4]+"-"+aaa[5]);
//		 }
		 
		 // 批量存入库
		 int num = 0;
		 int total = all.size();
		 int n = 1;
		 int size = 100000;
		 List<int[]> wan = new ArrayList<int[]>();
		 List<int[]> wan2 = new ArrayList<int[]>();
		 for(int[] one : all) {
			 wan.add(one);
			 num ++;
			 if(num < size) {
				 if(total-(n*size)>0) {
					 continue;
				 }else {
					 wan2.add(one);
					 continue;
				 }
			 }
			 for(int j = 1;j<=16;j++) {
				 batchInsert(wan,j);
				 System.out.println("--------n*size="+n+"x"+"100000"+"******"+j);
			 }
			 wan.removeAll(wan);
			 num = 0;
			 n ++;
		 }
		 for(int j = 1;j<=16;j++) {
			 batchInsert(wan2,j);
			 System.out.println("wan2*size---"+wan2.size()+"------j="+j);
		 }
	}
	 
	static void batchInsert(List<int[]> arr,int blue) {
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "insert into two_color_ball (r1,r2,r3,r4,r5,r6,blue) value (?,?,?,?,?,?,?)";
		PreparedStatement prest = null;;
		try {
			prest = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for (int i = 0; i < arr.size(); i++) {
				int[] one = arr.get(i);
				prest.setInt(1, one[0]);
				prest.setInt(2, one[1]);
				prest.setInt(3, one[2]);
				prest.setInt(4, one[3]);
				prest.setInt(5, one[4]);
				prest.setInt(6, one[5]);
				prest.setInt(7, blue);
				prest.addBatch();
			}
			prest.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(prest != null) {
				prest.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	static void batchInsertTmp(List<Integer> arr) {
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "insert into two_c_b_tmp (rownum,cnt) value (?,?)";
		PreparedStatement prest = null;;
		try {
			prest = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for (int i = 0; i < arr.size(); i++) {
				int one = arr.get(i);
				prest.setInt(1, one);
				prest.setInt(2, 1);
				prest.addBatch();
			}
			prest.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(prest != null) {
					prest.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static int r() {
		int A = 17721088;
		Random random = new Random();
		double d = random.nextDouble();
		return (int)(d*A) + 1;
	}
	
	static void getRandom(Long num) {
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0;i < num;i++) {
			int r = r();
			if(set.contains(r)) { // 里面出现超过1次的拿出来
				list.add(r);
			}
			set.add(r);
		}
		System.out.println(list.size());
		int size = 100000;
		List<Integer> sublist = new ArrayList<Integer>();
		for(int i =0;i<list.size(); i++) {
			sublist.add(list.get(i));
			if(sublist.size() == size) {
				batchInsertTmp(sublist);
				System.out.println("i===="+i+"sublist.size==="+sublist.size());
				sublist.removeAll(sublist);
			}
			if(i < list.size()-list.size()%size ) {
				continue;
			}else { // 最后剩的零头
				if(i==list.size()-1) {
					batchInsertTmp(sublist);
					System.out.println("i===="+i+"sublist.size==="+sublist.size());
				}
			}
		}
	}
	
	static List<Ball> selectALL(){
		List<Ball> list = new ArrayList<Ball>();
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "SELECT @rownum:=@rownum+1 AS rownum, two_color_ball.* " + 
				"FROM (SELECT @rownum:=0) r, two_color_ball";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Ball ball = new Ball();
				int rn = rs.getInt(1);
				int r1 = rs.getInt(2);
				int r2 = rs.getInt(3);
				int r3 = rs.getInt(4);
				int r4 = rs.getInt(5);
				int r5 = rs.getInt(6);
				int r6 = rs.getInt(7);
				int blue = rs.getInt(8);
				ball.setBlue(blue);
				ball.setR1(r1);
				ball.setR2(r2);
				ball.setR3(r3);
				ball.setR4(r4);
				ball.setR5(r5);
				ball.setR6(r6);
				ball.setRownum(rn);
				list.add(ball);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pst != null)pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	static List<Ball> selectTop(int topNum){
		List<Ball> list = new ArrayList<Ball>();
		Connection conn = DBUtil.getMySqlConnection();
		String sql = "select `rownum`,count(1) cnt FROM two_c_b_tmp group by `rownum` order by cnt desc limit " + topNum;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Ball ball = new Ball();
				int rn = rs.getInt(1);
				int cnt = rs.getInt(2);
				ball.setRownum(rn);;
				ball.setCnt(cnt);
				list.add(ball);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pst != null)pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	static List<Ball> selectTopPar(List<Ball> toplist){
		List<Ball> list = new ArrayList<Ball>();
		Connection conn = DBUtil.getMySqlConnection();
		String cond = "";
		for(int i = 0;i < toplist.size();i++) {
			Ball b = toplist.get(i);
			cond += i!=toplist.size()-1 ? b.getRownum() +"," : b.getRownum(); 
		}
		String sql = "select a.* from (SELECT @rownum:=@rownum+1 AS rownum, two_color_ball.* FROM (SELECT @rownum:=0) r, two_color_ball) a where a.rownum in";
			sql += "( " + cond + ")";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Ball ball = new Ball();
				int rn = rs.getInt(1);
				int r1 = rs.getInt(2);
				int r2 = rs.getInt(3);
				int r3 = rs.getInt(4);
				int r4 = rs.getInt(5);
				int r5 = rs.getInt(6);
				int r6 = rs.getInt(7);
				int blue = rs.getInt(8);
				ball.setBlue(blue);
				ball.setR1(r1);
				ball.setR2(r2);
				ball.setR3(r3);
				ball.setR4(r4);
				ball.setR5(r5);
				ball.setR6(r6);
				ball.setRownum(rn);
				list.add(ball);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pst != null)pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	static List<Ball> getRes(){
		List<Ball> rs = new ArrayList<Ball>();
		List<Ball> top = selectTop(10);
		List<Ball> xl =  selectTopPar(top);
		for(Ball ball : top) {
			for(Ball every : xl) {
				if(ball.getRownum() == every.getRownum()) {
					every.setCnt(ball.getCnt());
					rs.add(every);
				}
			}
		}
		return rs;
	}
	public static void main(String[] args) {
//		Long c = 10000000L;
		// 随机 c 次，把出现超过2次（含）以上的存进临时表，以便下面取出来
//		getRandom(c);
		List<Ball> r = getRes();
		for(Ball b : r) {
			System.out.println(b.toString());
		}
	}
}
