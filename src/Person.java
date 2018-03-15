import java.sql.*;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Person {
	int id;
	String fName,lName,mail;
	
	public static void main(String[] args) {
		
		/*Person kepo = new Person(4,"pirkas","ntounias","pirkas@email.com");
		//int kappa=kepo.SaveInDb(kepo.id, kepo.fName, kepo.lName, kepo.mail);
		System.out.println(kappa);
		*/
		
		
		//send email with java
		final String username="billzg1234@gmail.com";
		final String password ="g2155483";
		
		Properties props=new Properties();
		
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port","587");
		
		Session session = Session.getInstance(props,
						new javax.mail.Authenticator(){
							protected PasswordAuthentication getPasswordAuthentication(){
								return new PasswordAuthentication(username,password);
							}
						});
		try {
			
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("billzg1@hotmail.com") );
			message.setSubject("this is the subject");
			message.setText("this is the content");
			Transport.send(message);
			System.out.println("message sent");
			}catch (MessagingException e){
				throw new RuntimeException(e);
			}
		
		
		
		
	} //end of main
	public Person(int id,String fname,String lname,String mail){
		this.id=id;
		this.fName=fname;
		this.lName=lname;
		this.mail=mail;
		
	} // end of Person contrsuctor
	
	
	public static int SaveInDb(int id,String fname,String lname,String mail) {
		int status =0;//initialize the proccess 
		try {
			String ps = "insert into persons value("+id+",'"+fname+"','"+lname+"','"+mail+"')"; //this is correct bois.
			Connection con=DB.getConnection();
			PreparedStatement st = con.prepareStatement(ps);
			status=st.executeUpdate();
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		return status;
		
		
	}  //end of SaveInDb
	
	
	
}
