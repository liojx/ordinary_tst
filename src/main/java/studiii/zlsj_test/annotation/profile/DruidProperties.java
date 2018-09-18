package studiii.zlsj_test.annotation.profile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@PropertySource("file:config/server.properties")
@ConfigurationProperties(prefix = "druid")
public class DruidProperties {
    /**
     * MYSQL_DB config
     */
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
