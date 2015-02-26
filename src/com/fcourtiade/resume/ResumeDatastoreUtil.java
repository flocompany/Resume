package com.fcourtiade.resume;

import com.google.appengine.api.datastore.*;

public class ResumeDatastoreUtil {

	
	public static String getHtmlByData(boolean french){
		String result = "";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		
		
		Key cleResumeEnglish = KeyFactory.createKey("File", "resumeEnglish");
		Key cleResumeFrench = KeyFactory.createKey("File", "resumeFrench");
		Key cleCss = KeyFactory.createKey("File", "css");
		Key cleParam = KeyFactory.createKey("Parametre", "pwd");

		Entity resumeEnglish=null;
		Entity resumeFrench=null;
		Entity css=null;
		Entity pwd=null;
		try {
			resumeEnglish = datastore.get(cleResumeEnglish);
		} catch (EntityNotFoundException e) {
			Entity resume = new Entity("File", "resumeEnglish");
			resume.setProperty("desciption", "Florent Courtiade Resume in English");
			Text textHtml = new Text(ResumeFileUtil.getHtmlResume("WEB-INF/resume/resume.html"));
			resume.setProperty("content", textHtml);
			datastore.put(resume);
		}
		
		try {
			resumeFrench = datastore.get(cleResumeFrench);
		} catch (EntityNotFoundException e) {
			Entity cv = new Entity("File", "resumeFrench");
			cv.setProperty("desciption", "Florent Courtiade Resume in French");
			Text textHtml = new Text(ResumeFileUtil.getHtmlResume("WEB-INF/resume/cv.html"));
			cv.setProperty("content", textHtml);
			datastore.put(cv);

		}
			
		try {
			css = datastore.get(cleCss);
		} catch (EntityNotFoundException e) {
			Entity cssFile = new Entity("File", "css");
			cssFile.setProperty("desciption", "Florent Courtiade Resume css");
			Text textHtml = new Text(ResumeFileUtil.getHtmlResume("WEB-INF/resume/resume.css"));
			cssFile.setProperty("content",textHtml);
			datastore.put(cssFile);

		}
		
		
		try {
			pwd = datastore.get(cleParam);
		} catch (EntityNotFoundException e) {
			Entity paramPwd = new Entity("Parametre", "pwd");
			paramPwd.setProperty("desciption", "Password");
			paramPwd.setProperty("value","sdk2015");
			datastore.put(paramPwd);

		}
			

		try {
			css = datastore.get(cleCss);
			Text textCss = (Text) css.getProperty("content");
			String cssFile = textCss.getValue();
			if(french){
				resumeFrench = datastore.get(cleResumeFrench);
				Text textHtml = (Text) resumeFrench.getProperty("content");
				result = textHtml.getValue().replace("<!--includecssresume-->", cssFile);
			}else{
				resumeEnglish = datastore.get(cleResumeEnglish);
				Text textHtml = (Text) resumeEnglish.getProperty("content");
				result = textHtml.getValue().replace("<!--includecssresume-->", cssFile);
			}
		} catch (EntityNotFoundException e) {
			
		}
		
		
		return result;
	}
	
	
}
