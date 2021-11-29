package src;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {
    public static void SendMail(String recepient, String myAccount, String password, String subject, String text)
            throws Exception {
        System.out.println("preping to send email");
        Properties Properties = new Properties();
        Properties.put("mail.smtp.auth", "true");

        Properties.put("mail.smtp.starttls.enable", "true");
        Properties.put("mail.smtp.host", "smtp.gmail.com");
        Properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(Properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, password);
            }
        });
        Message message = prepareMessage(session, myAccount, recepient, subject, text);
        Transport.send(message);
        System.out.println("message sent!");
    }

    private static Message prepareMessage(Session session, String myAccount, String recepient, String subject,
            String text) {
        try {
            // create new empty message
            Message message = new MimeMessage(session);
            // sent from my account
            message.setFrom(new InternetAddress(myAccount));
            // set message recipient as recipient variable
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(subject);
            message.setText(text);
            return message;
        } catch (Exception e) {
            e.getMessage();
            e.getCause();
        }
        return null;

    }

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.print("Your Email:");
        String email = input.nextLine();
        System.out.print("Your Password:");
        String password = input.nextLine();

        System.out.print("Your Subject:");
        String subject = input.nextLine();
        System.out.print("Your Message:");
        String message = input.nextLine();
        System.out.print("Who to send email to ?");
        String recepient = input.nextLine();
        SendMail(recepient, email, password, subject, message);
    }
}