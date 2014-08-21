package org.hicon.service.mail;
import org.hicon.service.mail.facade.MailFacade;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import com.google.appengine.api.mail.MailServiceFactory;

import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
//import com.google.appengine.api.mail.MailService;

import javax.ws.rs.core.Response.ResponseBuilder;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ObjectOutputStream;
import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.activation.DataHandler;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import java.text.ParseException;



@Path("/Mail")
public class MailService {
    @GET
    @Path("/SendStatement")
    @Produces("text/html")
    public String SendMonthlyStatement() throws DocumentException,IOException,InstantiationException, IllegalAccessException,ParseException   {
        Properties props = new Properties();
        String message = "Success";
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "...";

        
            com.google.appengine.api.mail.MailService service = com.google.appengine.api.mail.MailServiceFactory.getMailService(); 
                                com.google.appengine.api.mail.MailService.Message msg = new com.google.appengine.api.mail.MailService.Message(); 

                        msg.setSender("sumon.sadhukhan8@gmail.com"); 
                        msg.setTo("sumon.sadhukhan8@gmail.com"); 

                        msg.setSubject("hi"); 
                        msg.setTextBody("hi"); 
                        com.google.appengine.api.mail.MailService.Attachment attachment = new com.google.appengine.api.mail.MailService.Attachment("statement.pdf",GenerateMonthlyPDFStatement());
                        msg.setAttachments(attachment);// file in binary
                        service.send(msg); 

        
        return message;

    }

    public byte[] GenerateMonthlyPDFStatement ()throws InstantiationException, IllegalAccessException,DocumentException,ParseException  {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("HI"));
        MailFacade facade = new MailFacade();
        facade.generateTable(document);
        document.close();

        return baos.toByteArray();

    }

}