package com.me.entity;

public class BankDataBean {
	  private String user_ID;
		private String password;
		private String firstName;
		private String lastName;
		private String email_ID;
		private String dateOfBirth;
		private String address;
		private String accountType;
		private String accountCreateDate;
		private int savingAcctNum;
		private int checkingAcctNum;
		private float checkingBal;
		private float savingBal;
		public int getSavingAcctNum() {
			return savingAcctNum;
		}
		public void setSavingAcctNum(int savingAcctNum) {
			this.savingAcctNum = savingAcctNum;
		}
		public int getCheckingAcctNum() {
			return checkingAcctNum;
		}
		public void setCheckingAcctNum(int checkingAcctNum) {
			this.checkingAcctNum = checkingAcctNum;
		}
		
		public float getCheckingBal() {
			return checkingBal;
		}
		public void setCheckingBal(float checkingBal) {
			this.checkingBal = checkingBal;
		}
		public float getSavingBal() {
			return savingBal;
		}
		public void setSavingBal(float savingBal) {
			this.savingBal = savingBal;
		}
		
		public String getUser_ID() {
			return user_ID;
		}
		public void setUser_ID(String user_ID) {
			this.user_ID = user_ID;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail_ID() {
			return email_ID;
		}
		public void setEmail_ID(String email_ID) {
			this.email_ID = email_ID;
		}
		public String getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getAccountType() {
			return accountType;
		}
		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}
		public String getAccountCreateDate() {
			return accountCreateDate;
		}
		public void setAccountCreateDate(String accountCreateDate) {
			this.accountCreateDate = accountCreateDate;
		}

}
