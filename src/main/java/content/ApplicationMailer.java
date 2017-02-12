package content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailService")
public class ApplicationMailer
{
    @Autowired
    private MailSender mailSender;

    @Autowired
    private SimpleMailMessage enquiryMessage;

    @Autowired
    private SimpleMailMessage reviewMessage;

    /**
     * This method will send compose and send the message
     * */
    public void sendMail(String to, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    /**
     * This method will send a pre-configured enquiry message
     * */
    public void sendNewEnquiryMail(String subject, String message, String fromAddress)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(enquiryMessage);

        if( fromAddress != null)
        {
            mailMessage.setReplyTo( fromAddress );
        }
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    /**
     * This method will send a pre-configured review message
     * */
    public void sendNewReviewMail(String message)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(reviewMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}
