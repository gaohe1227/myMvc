package com.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.Action;
/**
 * 
 * @author �ߺ�
 *
 * ����:ʵ����
 *
 * 2015��11��1��
 */
public class DemoAction extends Action{
 
	@Override
	public   String excute(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		// ����response���������Դ�����ҳ��
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		File file=new File("d:/Server.java");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		InputStream in=null;
		ServletOutputStream out = null;
		try {
			response.setHeader("Content-Disposition", "attachment;filename="+ new String("����".getBytes(), "iso-8859-1") + ".text");
			
			in=new FileInputStream(file);
			out=response.getOutputStream(); 
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(out); 
			byte[] buff = new byte[1024];
			while(-1!=bis.read(buff)){
				bos.write(buff);
			}
			
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null!=bis){
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=bos){
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
