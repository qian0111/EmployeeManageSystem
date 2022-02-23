package model;

import util.StrUtil;

public class Dept {
	
	private Integer id;
	private String deptName;
	private Integer managerId;
	private String managerName;
	private Long deptNumber;
	
	public Dept() {
		
	}	
	
	public Dept(String id, String deptName, String managerName) {
		if(!StrUtil.isEmpty(id)) {
			this.id = Integer.valueOf(id);
		}
		if(deptName != null) {
			this.deptName = deptName;
		}
		if(managerName != null) {
			this.managerName = managerName;
		}		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Long getDeptNumber() {
		return deptNumber;
	}

	public void setDeptNumber(Long deptNumber) {
		this.deptNumber = deptNumber;
	}
	
}
