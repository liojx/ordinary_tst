package studiii.zlsj_test.annotation.profile;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月18日 上午11:19:26
 * Modified By:
 */
@Component
public class DevProfile {
	  @Autowired
	  private DruidPropertiesDev properties;
		  
		public DataSource EmbeddedDatabaseBuilder() {
			Map<String, Object> propertiesMap = new HashMap<>();
	        propertiesMap.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, properties.getDriverClass());
	        propertiesMap.put(DruidDataSourceFactory.PROP_URL, properties.getUrl());
	        propertiesMap.put(DruidDataSourceFactory.PROP_USERNAME, properties.getUser());
	        propertiesMap.put(DruidDataSourceFactory.PROP_PASSWORD, properties.getPassword());
	        //添加统计、SQL注入、日志过滤器
	        propertiesMap.put(DruidDataSourceFactory.PROP_FILTERS, "stat,wall,log4j2");
	        //sql合并，慢查询定义为1s
	        propertiesMap.put(DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES, "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=1000");
	        try {
	            DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(propertiesMap);
	            dataSource.setUseGlobalDataSourceStat(true);
//	            合并多个DruidDataSource的监控数据
	            return dataSource;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
		}
	
}
