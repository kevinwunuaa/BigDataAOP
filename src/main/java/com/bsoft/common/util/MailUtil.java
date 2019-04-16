package com.bsoft.common.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

/**
 * 发送Email
 * @author 孤傲苍狼 2015-1-12 下午9:42:56
 * 
 */
public class MailUtil {
	public static String smtpHost = "mail.bsoft.com.cn";
	public static String sendUser = "wuyong";
	public static String mailPasswd = "9txM4e1";
	public static String mailAddr = "wuyong@bsoft.com.cn";

	/**
	 * 发送邮件.
	 * @param to 收件人地址
	 * @param subject 邮件主题
	 * @param content 内容
	 * @throws MessagingException
	 * @throws NamingException
	 */
	public static void sendMail(String to,String subject,String content)
			throws MessagingException, NamingException {

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sendUser, mailPasswd);
			}
		};
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.from", mailAddr);

		Session msgSession = Session.getInstance(props, auth);
		msgSession.setDebug(true);
		MimeMessage msg = new MimeMessage(msgSession);
		msg.setFrom();
		msg.setRecipients(Message.RecipientType.TO, to);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(content, "UTF-8");
//		msg.setContent("<h1>yyy</h1>", "text/html");	

		Transport.send(msg);

		System.out.println("邮件发送结束");
	}

	public static void main(String[] params) throws Exception {
		sendMail("597631904@qq.com","xxx","yyy");
	}
}
