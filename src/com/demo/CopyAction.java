package com.demo;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.Action;

public class CopyAction extends Action{

 
public static void main(String[] args) {

}
@Override
public String excute(HttpServletRequest request, HttpServletResponse response) {
	// TODO Auto-generated method stub
	System.out.println("���Ը���");
	try {
		request.setCharacterEncoding("utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	response.setCharacterEncoding("utf-8");
	request.setAttribute("test", "���Ը���");
	return "copy.jsp";
}
}
