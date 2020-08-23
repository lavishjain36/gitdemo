package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	customerDao=new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
     Customer  customer=new Customer();
     customer.setEmail("customer11@gmail.com");
     customer.setFirstname("Yash");
     customer.setLastname("Jain");
     customer.setCity("Udaipur");
     customer.setState("Rajasthan");
     customer.setCountry("India");
     customer.setAddressLine1("Rishabhdeo");
     customer.setAddressLine2("Sadar Bazar");
     customer.setPassword("qwerty");
     customer.setPhone("9087693442");
     customer.setZipcode("313802");
     
     Customer savedCustomer=customerDao.create(customer);
     assertTrue(savedCustomer.getCustomerId()>0);
	
	}

	@Test
	public void testGet() {
    Integer customerId=12;
    Customer customer=customerDao.get(customerId);
    
    assertNotNull(customer);
	
	}
	
	
	@Test
	public void testUpdateCustomer() {
		Customer customer=customerDao.get(12);
		String firstname="Nikhil";
		customer.setFirstname(firstname);
	    customer.setPassword("12345");	
	    
		Customer updatedCustomer=customerDao.update(customer);
		
		assertTrue(updatedCustomer.getFirstname().equals(firstname));
		
	}

	@Test
	public void testDeleteObject() {
      Integer customerId=12;
      customerDao.delete(customerId);
      Customer customer=customerDao.get(customerId);
      
      assertNull(customer);
      }
	
	@Test
	public void testListAll() {
		List<Customer> listCustomer =customerDao.listAll();
		
		for(Customer customer:listCustomer) {
			System.out.println(customer.getFirstname());
		}
		
		
		assertFalse(listCustomer.isEmpty());
	}
	
	
	@Test
	public void testCount() {
		long totalCustomers=customerDao.count();
		
		assertEquals(10, totalCustomers);
	}
	
	@Test
	public void testFindByEmail() {
		String email="monu@gmail.com";
		Customer customer=customerDao.findByEmail(email);
		
		assertNotNull(customer);
	}

	
	@Test
	public void testCheckLoginSuccess() {
		String email="monu@gmail.com";
		String password="monujain";
		
		Customer customer=customerDao.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email="abc@gmail.com";
		String password="monujain";
		
		Customer customer=customerDao.checkLogin(email, password);
		
		assertNull(customer);
	}
}
