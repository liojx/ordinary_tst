package studiii.zlsj_test.annotation.profile;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import studiii.zlsj_test.Application;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月18日 上午11:21:58
 * Modified By:
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)//这里的Application是springboot的启动类名
public class DevTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mockMvc;
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @Before
//    public void setupMockMvc() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
    
    @Autowired
    private ProdProfile pp ;
    
    @Autowired
    private DevProfile ppdev;
    
    @Test
    public void test() {
    	DataSource ds = pp.EmbeddedDatabaseBuilder();
    	try {
			Connection conn  = ds.getConnection();
			PreparedStatement pst = conn.prepareStatement("select * from user_props where user_id = ?");
			pst.setString(1, "92dfa80ee7024b55bce822ea598c471a");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println("---------------------user_props-------------------");
				System.out.println(rs.getObject(1));
				System.out.println(rs.getObject(2));
				System.out.println(rs.getObject(3));
				System.out.println(rs.getObject(4));
				System.out.println(rs.getObject(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	DataSource ds_dev = ppdev.EmbeddedDatabaseBuilder();
    	try {
    		System.out.println("----------------------------liojx-------------------");
    		Connection conn  = ds_dev.getConnection();
    		PreparedStatement pst = conn.prepareStatement("select * from liojx");
    		ResultSet rs = pst.executeQuery();
    		while(rs.next()) {
    			System.out.println(rs.getObject(1));
    			System.out.println(rs.getObject(2));
    			System.out.println(rs.getObject(3));
    		}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    }

}