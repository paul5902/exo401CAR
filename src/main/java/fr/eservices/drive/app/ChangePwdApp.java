package fr.eservices.drive.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import fr.eservices.drive.dao.IUserDao;
import fr.eservices.drive.model.User;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ChangePwdApp {
	
	@Autowired
	IUserDao userDao;

	public ChangePwdApp() {
	}
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	public void run() throws IOException {
		System.out.print("Login ? ");
		String login = read();
		
		User u = userDao.find(login);
		if ( u == null ) {
			System.out.println("No such user");
			return;
		}
		System.out.println( "Found user: " + u.getFirstname() + " " + u.getLastname() );
		
		System.out.print( "new password ? ");
		String pwd = read();
		userDao.setPassword(login, pwd);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("--- ChangePwdApp ---");
		System.out.println("Which impl do you want to load ?");
		System.out.println("  1 -> JDBC with MD5 checker, defined with application-context.xml");
		System.out.println("  2 -> JPA with Hmac checker, defined with annotations");
		System.out.println("  (other): exit");
		System.out.print(  "  choice ==> ");
		
		String choice = read();
		ConfigurableApplicationContext ctx = null;
		switch( choice ) {
		case "1":
			ctx = getXmlAppContext();
			break;
		case "2":
			ctx = getAnnotationAppContext();
			break;
			default: return;
		}
		
		ChangePwdApp app = ctx.getBean( ChangePwdApp.class );
		app.run();
		
	}
	
	public static ConfigurableApplicationContext getXmlAppContext() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		return ctx;
	}

	public static ConfigurableApplicationContext getAnnotationAppContext() throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		return ctx;
	}
	
	private static BufferedReader in;
	private static String read() throws IOException {
		if ( in == null ) {
			in = new BufferedReader( new InputStreamReader(System.in) );
		}
		return in.readLine();
	}

}
