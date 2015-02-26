package com.fcourtiade.resume;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;



@SuppressWarnings("serial")
public class Florent_courtiade_adminServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(Florent_courtiade_adminServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		resp.sendRedirect("/jsp/admin.jsp");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String pwd = req.getParameter("pwd");
		String cv = req.getParameter("cv");
		String css = req.getParameter("css");
		String resume = req.getParameter("resume");

		log.info("pwd string = " + pwd);
		
		boolean result = send(resume, css, cv, pwd);

		if(!result){
			req.setAttribute("error", "Erreur d'envoie");
		}else{
			req.setAttribute("error", "Envoyé!!");
		}
        try {
			req.getRequestDispatcher("/jsp/admin.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("servlet exception");
		}
	}
	
	
	private boolean send(String resume, String css, String cv, String pwd){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key cleParam = KeyFactory.createKey("Parametre", "pwd");
		try {
			Entity pwdEntity = datastore.get(cleParam);
		
			if 	(pwd!=null&&!pwd.equals("")){
				String pwdData = (String) pwdEntity.getProperty("value");
				if(!pwdData.equals(pwd)){
					return false;
				}
			}else{
				return false;
			}
		} catch (EntityNotFoundException e1) {
			return false;
		}
		
		if(resume.equals("")||resume.equals("resume")){
		}else{
			Entity resumeEnglish=null;
			Key cleResumeEnglish = KeyFactory.createKey("File", "resumeEnglish");
			try {
				resumeEnglish = datastore.get(cleResumeEnglish);
				Text textHtml = new Text(resume);
				resumeEnglish.setProperty("content", textHtml);
				datastore.put(resumeEnglish);
			} catch (EntityNotFoundException e) {
			}
		}
		
		
		if(cv.equals("")||cv.equals("cv")){
		}else{
			Entity resumeFrench=null;
			Key cleResumeFrench = KeyFactory.createKey("File", "resumeFrench");
			try {
				resumeFrench = datastore.get(cleResumeFrench);
				Text textHtml = new Text(cv);
				resumeFrench.setProperty("content", textHtml);
				datastore.put(resumeFrench);
			} catch (EntityNotFoundException e) {
				return false;
			}
		}
		
		
		if(css.equals("")||css.equals("css")){
		}else{
			Entity resumeCss=null;
			Key cleResumeCss = KeyFactory.createKey("File", "css");
			try {
				resumeCss = datastore.get(cleResumeCss);
				Text textHtml = new Text(css);
				resumeCss.setProperty("content", textHtml);
				datastore.put(resumeCss);
			} catch (EntityNotFoundException e) {
				return false;
			}
		}
		
		return true;
		
	}
}
