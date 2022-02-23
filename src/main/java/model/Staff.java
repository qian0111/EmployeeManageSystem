package model;

import util.StrUtil;

public class Staff {
	private Integer id;
	private String staffName;
	private String pass;
	private String phone;
	private Integer deptId;
	private String deptName;
	private Integer staffType;
	
	public Staff() {
		
	}
	
	public Staff(String staffName, String pass,String phone) {
		this.staffName = staffName;
		this.pass = pass;
		this.phone = phone;
	}
	
	public Staff(String id, String staffName, String phone, String deptId) {
		if(!StrUtil.isEmpty(id)) {
			this.id = Integer.valueOf(id);
		}
		if(staffName != null) {
			this.staffName = staffName;
		}
		if(phone != null) {
			this.phone = phone;
		}
		if(!StrUtil.isEmpty(deptId)) {
			this.deptId = Integer.valueOf(deptId);
		}		
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getStaffType() {
		return staffType;
	}
	public void setStaffType(Integer staffType) {
		this.staffType = staffType;
	}

	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
