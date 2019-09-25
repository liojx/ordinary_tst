package studiii.zlsj_test.util.pgdb;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: liaosijun
 * @Time: 2019/9/25 15:01
 */
@Data
@ToString
public class ShareDev {
	private Long id;
	private Long type_id;
	private String name;
	private Short status;
	private String sn;
	private String lng;
	private String lat;
	private String addr;
	private String platform_code;
	private String create_by;
	private Date create_at;
	private String update_by;
	private Date update_at;
	private Integer del_flag;
	private Integer caliber;
}
