package com.base;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * Servlet implementation class MyMvcServlet
 */
@WebServlet("/MyMvcServlet")
public class MyMvcServlet extends HttpServlet {
	  static Map<String,Object>   xmlBeanMap=new HashMap<String, Object>();
	  static Map<String,Object>  webServlet=new HashMap<String, Object>();
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub 
		super.init();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		 super.init(config); 
	  
	  
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMvcServlet() {
        super();
         
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String keyUrl=request.getRequestURI();
	  String url=keyUrl;
	  System.out.println(url);
	  if(url.indexOf("?")!=-1){
		  url=url.substring(0, url.indexOf("?")-1);
		 
	  }
	  response.setCharacterEncoding("utf-8");
	 // RequestDispatcher dispatcher=null;
	  try {
	  if(null!=xmlBeanMap.get(url)){ 
			  Class clazz=Class.forName((String) xmlBeanMap.get(keyUrl));//�����ȡ��
			  System.out.println(null==clazz);
			 Object action=   clazz.newInstance();//����ʵ��
			 System.out.println((String) xmlBeanMap.get(keyUrl)+ "---"+ action);
			 Method method=clazz.getDeclaredMethod("excute", HttpServletRequest.class,HttpServletResponse.class); 
			  Object objectUrl=  method.invoke(action, request,response);//ִ�з���
			 if(null!=objectUrl){ 
				 request.getRequestDispatcher(objectUrl.toString()).forward(request, response);//�ض���
			 } 
	  }else if(null!=webServlet.get(url)) {
		  response.sendRedirect(keyUrl);//ת��  
		 
	  } else{
		  response.sendRedirect("404.jsp");
	  }
	  }catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	} 

	public static void intixmlBeanMap(ServletContext servletContext, Document document) throws ClassNotFoundException, MyActionException {
	 
	  Element root=document.getRootElement();
	   List<Element> beansElements=root.elements("bean");
	   if(null!=beansElements&&beansElements.size()>0){
		     for (Element element : beansElements) { 
		    	 if(element.attributeValue("url")==null){
		    		 throw new MyActionException("id��"+element.attributeValue("id")+"��Ԫ��·��������");
		    	 }else if(xmlBeanMap.get(element.attributeValue("url"))!=null){
		    		 throw new MyActionException("·��"+element.attributeValue("url")+"�ظ�");
		    	 }
		    	 else{
		    		 Class clazz=Class.forName(element.attributeValue("name")); 
		    		 if(clazz.getSuperclass().getName()!="com.base.Action"){
		    			 throw new MyActionException("id��"+element.attributeValue("id")+"��Ԫ�ظ��಻��Action");
		    		 }else{
		    			 xmlBeanMap.put(servletContext.getContextPath()+element.attributeValue("url"), element.attributeValue("name")); 
		    		 }
			    	 
		    	 } 
		    	 
			}
	   }
	   if(xmlBeanMap.size()>0){
		   Set<String> keys= xmlBeanMap.keySet();
		   for (String key : keys) {
			  System.out.println("����·��:"+xmlBeanMap.get(key)+"---------"+key);
		}
	   }
	  
	   
		
	}

}
