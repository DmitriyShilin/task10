package service;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class EmailValidatorTest {
	
	LinkedList<String> email;
	LinkedList<String> notEmail;
	
	@Before
	public void addToList() {
		email = new LinkedList<>();
		email.add("asd@gmail.com");
		email.add("a123sd@gmail.com");
		email.add("kot1999@mail.ru");
		email.add("fly2005@i.ua");
		email.add("11skyrim@ukr.net");
		email.add("petro789@meta.ua");
		email.add("medved_66@yandex.ru");
		email.add("MMM999fry@ua.fm");
		notEmail = new LinkedList<>();
		notEmail.add("13131313");
		notEmail.add("@@mail.com");
		notEmail.add("serg777@.gmail.com");
		notEmail.add("vova1918@vova1918");
		notEmail.add("//@//");
		notEmail.add("gogaVSgivicom.ua");
		notEmail.add("qwerty");
		notEmail.add("lina..@mail.ru");		
	}
	
	@Test
	public void testValidate() {
		for(String e: email) {
			assertTrue(EmailValidator.validate(e));
		}
		for(String n: notEmail) {
			assertTrue(!EmailValidator.validate(n));
		}		
	}
}
