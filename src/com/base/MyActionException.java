package com.base;
/**
 * 
 * @author 高鹤
 *
 * 作用:自定义异常类
 *
 * 2015年11月1日
 */
public class MyActionException extends Exception{
 private String message;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}
	public MyActionException(String message) {
		super();
		this.message = message;
	}
  
}
