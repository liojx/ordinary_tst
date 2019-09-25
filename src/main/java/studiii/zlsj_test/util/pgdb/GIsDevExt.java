package studiii.zlsj_test.util.pgdb;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: liaosijun
 * @Time: 2019/9/25 15:08
 */
@Data
@ToString
public class GIsDevExt {
	private Long id;
	private Long dev_id;
	private String name;
	private String code;
	private Integer caliber;
	private String material;
	private Object geom;
	private Long tpl_type_id;
	private Object data_info;
	private Boolean delete_flag;
	private String create_by;
	private Date create_at;
	private String update_by;
	private Date update_at;
}
