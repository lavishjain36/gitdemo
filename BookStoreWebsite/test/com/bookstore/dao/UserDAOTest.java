package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpClass() throws Exception {
		userDAO = new UserDAO();

	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("qoora@gmail.com");
		user1.setFullName("qoora");
		user1.setPassword("noora");
	
		user1 = userDAO.create(user1);


		assertTrue(user1.getUserId() > 0);

	}

	@Test(expected =PersistenceException.class)
	public void testCreateUsersFieldNotSet() {
		Users user1 = new Users();

		user1 = userDAO.create(user1);

		
	}
	
	 @Test
	    public void testUpdateUsers()
	    {
	    	Users user=new Users();
	    	user.setUserId(1);
	    	user.setEmail("lavishjain36@gmail.com");
	    	user.setFullName("Lavish Kushal");
	    	user.setPassword("qwerty");
	    	
	    	user=userDAO.update(user);
	    	String expected="qwerty";
	    	String actual=user.getPassword();
	    	
	    	assertEquals(expected, actual);
	    }
	    
	
	
	 
	 
	 
	 @Test
	    public void testGetUserFound()
	    {
	    	Integer userId=1;
	    	Users user=userDAO.get(userId);
	    	
	    	if(user!=null)
	    	{
	    	System.out.println(user.getEmail());
	    	}
	    	assertNotNull(user);
	    }
	
	 
	 @Test
	    public void testGetUserNotFound()
	    {
	    	Integer userId=99;
	    	Users user=userDAO.get(userId);
	    	
	    	
	    	assertNull(user);
	    }

	 @Test
	 public void testDeleteUsers()
	 {
	 	Integer userId=10;
	 	userDAO.delete(userId);
	 	
	 	Users user=userDAO.get(userId);
	 	assertNull(user);
	   }
	     
	 
	 @Test(expected = EntityNotFoundException.class)
	 public void testDeleteNonExistUsers()
	 {
	 	Integer userId=55;
	 	userDAO.delete(userId);
	 }
	 
	 @Test
	 public  void testListAll()
	{
		List<Users> listUsers=userDAO.listAll();
		
		for(Users user:listUsers)
		{
			System.out.println(user.getEmail());
		}
		
		assertTrue(listUsers.size()>0);
	}

	 
	 @Test
	 public void testCount()
	 {
	 	long totalUsers=userDAO.count();
	 	assertTrue(totalUsers>0);
	 //	assertEquals(9, totalUsers);
	 }
	 
	 
	 @Test
	 public void testCheckLoginSuccess() {
		 String email="lavishjain36@gmail.com";
		 String password="qwerty";
	boolean loginResult=userDAO.checkLogin(email,password);
	assertTrue(loginResult);
	 }
	 
	 
	 @Test
	 public void testCheckLoginFailed() {
		 String email="lavishjain36@gmail.com";
		 String password="qwerty1";
	boolean loginResult=userDAO.checkLogin(email,password);
	assertFalse(loginResult);
	 }
	 
	 @Test
	 public void testFindByEmail() {
		 String email="lavishjain36@gmail.com";
		 Users user=userDAO.findByEmail(email);
		 assertNotNull(user);
	 }
	 
	@AfterClass
	public static void tearDown() throws Exception {
		userDAO.close();
		// BaseDAOTest.tearDownAfterClass();
	}

}
