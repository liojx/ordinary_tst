package studiii.zlsj_test.annotation.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: liaosijun
 * Description:
 * DateTime: 2018年9月18日 上午11:16:44
 * Modified By:
 */
@Setter
@Getter
@Component
@PropertySource("file:config/dev.properties")
@ConfigurationProperties(prefix = "lsj")
public class DruidPropertiesDev {
	private String driverClass;
    private String url;
    private String user;
    private String password;

    private int     maxActive;
    private int     minIdle;
    private int     initialSize;
    private boolean testOnBorrow;
    private int maxWait;
}
