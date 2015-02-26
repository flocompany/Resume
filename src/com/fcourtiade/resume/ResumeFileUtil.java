package com.fcourtiade.resume;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ResumeFileUtil {

	private static final Logger log = Logger.getLogger(ResumeFileUtil.class.getName());
	
	
	/** Old implementation with file only
	 * @param french
	 * @return
	 */
	public static String getHtmlByFile(boolean french){
		
		String html="";
		String css="";
		File resumeHtml;
		if(french){
			resumeHtml = new File("WEB-INF/resume/cv.html");
		}else{
			resumeHtml = new File("WEB-INF/resume/resume.html");
		}
		File resumeCss = new File("WEB-INF/resume/resume.css");
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(resumeHtml); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				html+=ligne;
			}
			br.close(); 
			
			
			InputStream ipsCss=new FileInputStream(resumeCss); 
			InputStreamReader ipsrCss=new InputStreamReader(ipsCss);
			BufferedReader brCss=new BufferedReader(ipsrCss);
			String ligneCss;
			while ((ligneCss=brCss.readLine())!=null){
				System.out.println(ligneCss);
				css+=ligneCss;
			}
			br.close(); 
			
			html = html.replace("<!--includecssresume-->", css);
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return html;
	}
	
	
	/** get the resume.html or cv.html
	 * @param french
	 * @return
	 */
	public static String getHtmlResume(String filePath){
		
		String result="";
		File resumeHtml;
		resumeHtml = new File(filePath);
	
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(resumeHtml); 
			InputStreamReader ipsr=new InputStreamReader(ips, "UTF-8");
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				result+=ligne;
			}
			br.close(); 
		}catch (Exception e){
				System.out.println(e.toString());
		}
		return result;
	}
	
}
