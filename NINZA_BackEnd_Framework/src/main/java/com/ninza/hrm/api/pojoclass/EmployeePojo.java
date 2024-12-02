package com.ninza.hrm.api.pojoclass;

public class EmployeePojo {
	
	// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
	// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
	/* ObjectMapper om = new ObjectMapper();
	Root root = om.readValue(myJsonString, Root.class); */
	
	    public String designation;
	    public String dob;
	    public String email;
	    public String empName;
	    public int experience;
	    public String mobileNo;
	    public String project;
	    public String role;
	    public String username;
		public EmployeePojo() {
			super();
		}
		public EmployeePojo(String designation, String dob, String email, String empName, int experience,
				String mobileNo, String project, String role, String username) {
			super();
			this.designation = designation;
			this.dob = dob;
			this.email = email;
			this.empName = empName;
			this.experience = experience;
			this.mobileNo = mobileNo;
			this.project = project;
			this.role = role;
			this.username = username;
		}
		public String getDesignation() {
			return designation;
		}
		public void setDesignation(String designation) {
			this.designation = designation;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public int getExperience() {
			return experience;
		}
		public void setExperience(int experience) {
			this.experience = experience;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		public String getProject() {
			return project;
		}
		public void setProject(String project) {
			this.project = project;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
	    
	    
	

}