package com.got.vo.file;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileVO {
	
	private int f_no, save_name;
	private String save_path, real_name;
	private Timestamp regdate;
	
	public int getF_no() {
		return f_no;
	}
	public void setF_no(int f_no) {
		this.f_no = f_no;
	}
	public int getSave_name() {
		return save_name;
	}
	public void setSave_name(int save_name) {
		this.save_name = save_name;
	}
	public String getSave_path() {
		return save_path;
	}
	public void setSave_path(String save_path) {
		this.save_path = save_path;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "FileVO [f_no=" + f_no + ", save_name=" + save_name + ", save_path=" + save_path + ", real_name="
				+ real_name + ", regdate=" + regdate + "]";
	}
}
