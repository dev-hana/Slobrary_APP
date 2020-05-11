package com.example.autobrary.mail;

import java.util.ArrayList;
import java.util.HashMap;

public class Sender {
	public Sender(String subject, String contents, String sendTo) {
		String sendText = "<table class=\"wrapper\" style=\"border-collapse: collapse; table-layout: fixed; min-width: 320px; width: 100%; background-color: #fbfbfb;\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td>\r\n" + 
				"<div>\r\n" + 
				"<div style=\"margin: 0 auto; max-width: 560px; min-width: 280px; width: calc(28000% - 167440px);\">\r\n" + 
				"<div style=\"border-collapse: collapse; display: table; width: 100%;\">\r\n" + 
				"<div style=\"display: table-cell; float: left; font-size: 12px; line-height: 19px; max-width: 280px; min-width: 140px; width: calc(14000% - 78120px); padding: 10px 0 5px 0; color: #999; font-family: Georgia,serif;\">&nbsp;</div>\r\n" + 
				"<div style=\"display: table-cell; float: left; font-size: 12px; line-height: 19px; max-width: 280px; min-width: 139px; width: calc(14100% - 78680px); padding: 10px 0 5px 0; text-align: right; color: #999; font-family: Georgia,serif;\">&nbsp;</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div style=\"margin: 0 auto; max-width: 600px; min-width: 320px; width: calc(28000% - 167400px);\">\r\n" + 
				"<div style=\"font-size: 26px; line-height: 32px; color: #41637e; font-family: Avenir,sans-serif; margin: 6px 20px 20px 20px;\" align=\"center\">\r\n" + 
				"<div align=\"center\"><img style=\"display: block; height: auto; width: 100%; border: 0; max-width: 200px;\" src=\"http://mail.slobrary.com/logo.png\" width=\"200\" /></div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div>\r\n" + 
				"<div style=\"margin: 0 auto; max-width: 600px; min-width: 320px; width: calc(28000% - 167400px); overflow-wrap: break-word; word-wrap: break-word; word-break: break-word;\">\r\n" + 
				"<div style=\"border-collapse: collapse; display: table; width: 100%; background-color: #ffffff;\">\r\n" + 
				"<div style=\"text-align: left; color: #565656; font-size: 14px; line-height: 21px; font-family: Georgia,serif;\">\r\n" + 
				"<div style=\"margin: 24px 20px 24px 20px;\">\r\n" + 
				"<div style=\"mso-line-height-rule: exactly; mso-text-raise: 11px; vertical-align: middle;\">\r\n" + 
				"<h1 style=\"margin-top: 0; margin-bottom: 0; font-style: normal; font-weight: normal; color: #565656; font-size: 22px; line-height: 31px; font-family: Avenir,sans-serif;\">" + subject + "</h1>\r\n" + 
				"<p style=\"margin-top: 20px; margin-bottom: 0;\">"+ contents +"</p>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div style=\"mso-line-height-rule: exactly; line-height: 20px; font-size: 20px;\">&nbsp;</div>\r\n" + 
				"<div>\r\n" + 
				"<div style=\"margin: 0 auto; max-width: 600px; min-width: 320px; width: calc(28000% - 167400px); overflow-wrap: break-word; word-wrap: break-word; word-break: break-word;\">\r\n" + 
				"<div style=\"border-collapse: collapse; display: table; width: 100%;\">\r\n" + 
				"<div style=\"text-align: left; font-size: 12px; line-height: 19px; color: #999; font-family: Georgia,serif; float: left; max-width: 400px; min-width: 320px; width: calc(8000% - 47600px);\">\r\n" + 
				"<div style=\"margin: 10px 20px 10px 20px;\">\r\n" + 
				"<table class=\"email-footer__links\" style=\"border-collapse: collapse; table-layout: fixed;\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"padding: 0; width: 26px;\"><a style=\"text-decoration: underline; transition: opacity 0.1s ease-in; color: #999;\" href=\"https://www.slobrary.com\" rel=\"noreferrer noopener\"><img style=\"border: 0;\" src=\"https://i7.createsend1.com/static/eb/beta/13-the-blueprint-3/images/website.png\" alt=\"Website\" width=\"26\" height=\"26\" /></a></td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"<div style=\"font-size: 12px; line-height: 19px; margin-top: 20px;\">\r\n" + 
				"<div>SL:O Brary&nbsp;<br />Contact : support@slobrary.com<br />Website : https://www.slobrary.com<br />이 메일은 발신전용 메일입니다.</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div style=\"text-align: left; font-size: 12px; line-height: 19px; color: #999; font-family: Georgia,serif; float: left; max-width: 320px; min-width: 200px; width: calc(72200px - 12000%);\">\r\n" + 
				"<div style=\"margin: 10px 20px 10px 20px;\">&nbsp;</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div style=\"margin: 0 auto; max-width: 600px; min-width: 320px; width: calc(28000% - 167400px); overflow-wrap: break-word; word-wrap: break-word; word-break: break-word;\">\r\n" + 
				"<div style=\"border-collapse: collapse; display: table; width: 100%;\">\r\n" + 
				"<div style=\"text-align: left; font-size: 12px; line-height: 19px; color: #999; font-family: Georgia,serif;\">&nbsp;</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div style=\"line-height: 40px; font-size: 40px;\">&nbsp;</div>\r\n" + 
				"</div>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>";
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", sendTo);
		list.add(map);
//		map = new HashMap<String,String>();
//		map.put("email", "xxxx@gmail.com");
//		map.put("name", "알통2");
//		list.add(map);
		MailConnecter msm = new MailConnecter(subject, sendText, list);
		msm.start();
	}
}