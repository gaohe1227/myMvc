package com.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  /**
   * 
   * @author �ߺ�
   *
   * ����:������
   *
   * 2015��11��1��
   */
public abstract class Action {
	public abstract String excute(HttpServletRequest request,HttpServletResponse response);
  
}
