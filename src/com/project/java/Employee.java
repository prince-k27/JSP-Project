package com.project.java;

public class Employee {
	    protected int eid;
	    protected String ename;
	    protected String emailid;
	    protected long contact;
	    protected String status;
	 
	    public Employee() {
	    }
	 
	    public Employee(int eid) {
	        this.eid = eid;
	    }

		public Employee(int eid, String ename, String emailid, long contact, String status) {
			
			this.eid = eid;
			this.ename = ename;
			this.emailid = emailid;
			this.contact = contact;
			this.status = status;
		}

		public Employee(String ename, String emailid, long contact, String status) {
			
			this.ename = ename;
			this.emailid = emailid;
			this.contact = contact;
			this.status = status;
		}

		public int getEid() {
			return eid;
		}

		public void setEid(int eid) {
			this.eid = eid;
		}

		public String getEname() {
			return ename;
		}

		public void setEname(String ename) {
			this.ename = ename;
		}

		public String getEmailid() {
			return emailid;
		}

		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}

		public long getContact() {
			return contact;
		}

		public void setContact(long contact) {
			this.contact = contact;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	    

}
