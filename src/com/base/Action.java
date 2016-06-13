package com.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  /**
   * 
   * @author 高鹤
   *
   * 作用:基础类
   *
   * 2015年11月1日
   */
public abstract class Action {
	public abstract String excute(HttpServletRequest request,HttpServletResponse response);
  
}
