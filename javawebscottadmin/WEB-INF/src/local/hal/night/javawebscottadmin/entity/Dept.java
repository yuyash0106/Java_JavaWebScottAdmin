package local.hal.night.javawebscottadmin.entity;

/**
 * JavaWebDB Lesson Chap01 Src01
 *
 * 部門エンティティ。
 *
 * @author yuyas
 */
public class Dept {
	/**
	 * 部門番号
	 */
	private Integer _deptno;
	/**
	 * 部門名
	 */
	private String _dname = "";
	/**
	 * 所在地
	 */
	private String _loc = "";

	//以下アクセサメソッド。

	public Integer getDeptno() {
		return _deptno;
	}

	public void setDeptno(Integer deptno) {
		_deptno = deptno;
	}

	public String getDname() {
		return _dname;
	}

	public void setDname(String dname) {
		_dname = dname;
	}

	public String getLoc() {
		return _loc;
	}

	public void setLoc(String loc) {
		_loc = loc;
	}
}
