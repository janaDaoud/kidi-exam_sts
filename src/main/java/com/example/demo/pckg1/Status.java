package com.example.demo.pckg1;
//import lombok.Getter;


public enum Status {
Active, InActive, Pending;}

/*
public enum Status {
Active("Active"), InActive("InActive"), Pending("Pending");

 private String text;
 
 Status(String text){
	 this.text=text;
 }
 
 public String getText() {
	 return this.text;
 }
 
 public static Status fromString(String text) {
	 for(Status b : Status.values()) {
		 if(b.text.equalsIgnoreCase(text)) {
		 return b;
		 }
	 }
	return null; 
 }

}
 
 */