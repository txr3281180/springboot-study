package com.txr.spbbasic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTests {

	@Autowired
	private JavaMailSender javaMailSender;


	@Test
	public void contextLoads() {
		sendMail();
	}

	private void sendMail() {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			//MimeUtility.encodeWord("A_乱码测试", "UTF-8", "B")

			helper.setFrom("747632466@qq.com");
			//helper.setFrom("dataservice@sumscope.com"); //公司发件箱
			helper.setTo("xinrui.tian@sumscope.com");
			helper.setSubject("测试邮件");
			helper.setText("<p><span style=\"color:red;\">测试邮件</span></p>", true);

			//发送附件
			//helper.addAttachment("1.jpg", new File("url"));

			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
