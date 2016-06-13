package com.base;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ActionLiseter implements ServletContextListener{
  
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("--------------------------��Ŀ����");
	}



	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		// TODO Auto-generated method stub   
		File file=new File(contextEvent.getServletContext().getRealPath("/WEB-INF/web.xml"));//�����ļ�����
		 SAXReader saxReader=new SAXReader();
		 Document document=null; 
		String path="/WEB-INF/applicationContext.xml"; //�����ļ�·��
			 try { 
				 document=saxReader.read(file);//����xml�ĵ�����
				// Element root = document.getRootElement();//��ȡ��Ԫ�� 
				 List<Element> elements=document.getRootElement().elements("servlet"); 
				 if(null!=elements&&elements.size()>0){
					 for (Element element : elements) {
					   Element nodeElement=	 element.element("servlet-class");
					   String classNamne=nodeElement.getTextTrim();
					   if(classNamne.equals("com.base.MyMvcServlet")){
						   nodeElement=	 element.element("init-param"); 
						   if(null!=nodeElement){
							   nodeElement=nodeElement.element("param-value");
							   path=nodeElement.getTextTrim();//��ȡ·��
						   } 
						   break ;
					   } 
					}
				 } 
				 document=saxReader.read(new File(contextEvent.getServletContext().getRealPath(path)));//����xml�ĵ�����
				 MyMvcServlet.intixmlBeanMap(contextEvent.getServletContext(),document); 
				 elements=document.getRootElement().elements("servlet-mapping");
				 if(null!=elements&&elements.size()>0){
					 for (Element element : elements) {
					   Element nodeElement=	 element.element("servlet-name");
					    nodeElement.getTextTrim();
					   Element urlElement=	 element.element("servlet-url-pattern");
					   MyMvcServlet.webServlet.put(contextEvent.getServletContext().getContextPath()+nodeElement.getTextTrim(), urlElement.getTextTrim());
					}
				 }
			} catch (DocumentException e) { 
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MyActionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		
		
	}

}
