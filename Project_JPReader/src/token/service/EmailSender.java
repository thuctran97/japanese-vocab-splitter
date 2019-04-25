package token.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	@Autowired
	JavaMailSender mailSender;
	
	public void sendEmail(String toEmail, String subject, String body) {
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom("thucctin@gmail.com", "EShop Web Master");
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(body, true);
			FileSystemResource file = new FileSystemResource("flashcards.zip");
			helper.addAttachment(file.getFilename(), file);
			mailSender.send(mail);
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}