package studiii.zlsj_test.util.pgdb;

import org.postgis.PGgeometry;

/**
 * @Author: liaosijun
 * @Time: 2019/10/10 10:56
 */
public class PointNow {
	private Long id;
	private Long typeId;
	private String name;
	private String code;
	private Integer kj;
	private PGgeometry geom;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getKj() {
		return kj;
	}

	public void setKj(Integer kj) {
		this.kj = kj;
	}

	public PGgeometry getGeom() {
		return geom;
	}

	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}
}
