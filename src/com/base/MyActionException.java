package com.base;
/**
 * 
 * @author �ߺ�
 *
 * ����:�Զ����쳣��
 *
 * 2015��11��1��
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
