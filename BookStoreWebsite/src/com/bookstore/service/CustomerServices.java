package com.bookstore.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Message;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.Customer;

public class CustomerServices {

	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.customerDAO=new CustomerDAO();
	}
	
	public void listCustomers(String message) throws ServletException, IOException {
		
		List<Customer> listCustomer=customerDAO.listAll();
		
		if(message!=null) {
			request.setAttribute("message", message);
		}
		
		request.setAttribute("listCustomer", listCustomer);
		
		String listPage="customer_list.jsp";
	    RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
	    requestDispatcher.forward(request, response);
	
	}
	
	public void listCustomers() throws ServletException, IOException {
     listCustomers(null);
	}
	
	public void createCustomer() throws ServletException, IOException {
		String email=request.getParameter("email");
		Customer existCustomer=customerDAO.findByEmail(email);
		
		
		if(existCustomer!=null) {
			String message="Could not create new customer: the email "
		             + email + "is already registered by another customer.";
			
			listCustomers(message);
		}
		else {
			String firstname=request.getParameter("firstname");
			String lastname=request.getParameter("lastname");
			String password=request.getParameter("password");
			String phone=request.getParameter("phone");
            String addressLine1=request.getParameter("address1");
            String addressLine2=request.getParameter("address2");
            String city=request.getParameter("city");
            String state=request.getParameter("state");
            String zipCode=request.getParameter("zipcode");
            String country=request.getParameter("country");
            
            Customer newCustomer=new Customer();
            newCustomer.setEmail(email);
            newCustomer.setFirstname(firstname);
            newCustomer.setLastname(lastname);
            newCustomer.setPassword(password);
            newCustomer.setPhone(phone);
            newCustomer.setAddressLine1(addressLine1);
            newCustomer.setAddressLine2(addressLine2);
            newCustomer.setCity(city);
            newCustomer.setState(state);
            newCustomer.setZipcode(zipCode);
            newCustomer.setCountry(country);
            
            customerDAO.create(newCustomer);
            
            String message="New customer has been created successfully.";
            listCustomers(message);

			
		}
		
	}
	
	
	public void updateCustomerFieldsFromForm(Customer customer) {
		String email=request.getParameter("email");
        String firstname=request.getParameter("firstname");
        String lastname=request.getParameter("lastname");
        String password=request.getParameter("password");
		String phone=request.getParameter("phone");
        String addressLine1=request.getParameter("address1");
        String addressLine2=request.getParameter("address2");
        String city=request.getParameter("city");
        String state=request.getParameter("state");
        String zipCode=request.getParameter("zipcode");
        String country=request.getParameter("country");
        
        if(email!=null && !email.equals("")) {
        customer.setEmail(email);
        }
        
        customer.setFirstname(firstname);
        customer.setLastname(lastname);

        if(password!=null && !password.equals("")) {
        customer.setPassword(password);
        }
        customer.setPhone(phone);
        customer.setAddressLine1(addressLine1);
        customer.setAddressLine2(addressLine2);
        customer.setCity(city);
        customer.setState(state);
        customer.setZipcode(zipCode);
        customer.setCountry(country);
        
	}
	
	public void registerCustomer() throws ServletException, IOException {
		String email=request.getParameter("email");
		Customer existCustomer=customerDAO.findByEmail(email);
		String message="";
		
		if(existCustomer!=null) {
			 message="Could not register. The email "
		             + email + " is already registered by another customer.";
			
		}
		else {
			String firstname=request.getParameter("firstname");
			String lastname=request.getParameter("lastname");

			String password=request.getParameter("password");
			String phone=request.getParameter("phone");
            String addressLine1=request.getParameter("address1");
            String addressLine2=request.getParameter("address2");
            String city=request.getParameter("city");
            String state=request.getParameter("state");
            String zipCode=request.getParameter("zipcode");
            String country=request.getParameter("country");
           
            Customer newCustomer=new Customer();
            newCustomer.setEmail(email);
            newCustomer.setFirstname(firstname);
            newCustomer.setLastname(lastname);
            newCustomer.setPassword(password);
            newCustomer.setPhone(phone);
            newCustomer.setAddressLine1(addressLine1);
            newCustomer.setAddressLine2(addressLine2);
            newCustomer.setCity(city);
            newCustomer.setState(state);
            newCustomer.setZipcode(zipCode);
            newCustomer.setCountry(country);
            
            customerDAO.create(newCustomer);
            
             message="You have registered successfully! Thank you.<br/>"
            		 + "<a href='login'>Click here </a> to login";

			
		}
		
		String messagePage="frontend/message.jsp";
	    RequestDispatcher requestDispatcher=request.getRequestDispatcher(messagePage);
	    request.setAttribute("message", message);
	    
	    requestDispatcher.forward(request, response);
		
	}


	public void editCustomer() throws ServletException, IOException {
      
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		Customer customer=customerDAO.get(customerId);
		
		request.setAttribute("customer", customer);
		
		CommonUtility.generateCountryList(request);

		String editPage="customer_form.jsp";
	    RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
	    requestDispatcher.forward(request, response);
		
	}
//edit customer
	public void updateCustomer() throws ServletException, IOException {

		Integer customerId=Integer.parseInt(request.getParameter("customerId"));
		String email=request.getParameter("email");
		
		Customer CustomerByEmail=customerDAO.findByEmail(email);
		String message=null;
		
		if(CustomerByEmail!=null && CustomerByEmail.getCustomerId()!=customerId) {
			 message="Could not update the customer ID " + customerId
					+ " because there's an existing customer having the same email.";
		}else {
			
			
			String firstname=request.getParameter("firstname");
			String lastname=request.getParameter("lastname");
			String password=request.getParameter("password");
			String phone=request.getParameter("phone");
            String addressLine1=request.getParameter("address1");
            String addressLine2=request.getParameter("address2");
            String city=request.getParameter("city");
            String state=request.getParameter("state");
            String zipCode=request.getParameter("zipcode");
            String country=request.getParameter("country");
            
            Customer customerById=customerDAO.get(customerId);
         //   customerById.setCustomerId(customerId);
            customerById.setEmail(email);
            customerById.setFirstname(firstname);
            customerById.setLastname(lastname);
            customerById.setPassword(password);
            customerById.setPhone(phone);
            customerById.setAddressLine1(addressLine1);
            customerById.setAddressLine2(addressLine2);
            customerById.setCity(city);
            customerById.setState(state);
            customerById.setZipcode(zipCode);
            customerById.setCountry(country);
            
            customerDAO.update(customerById);
            
             message="The customer has been updated successfully.";
            
          	}
		listCustomers(message);
		
	}

	public void deleteCustomer() throws ServletException, IOException {

		Integer customerId=Integer.parseInt(request.getParameter("id"));
        customerDAO.delete(customerId);
        
        String message="The customer has been deleted successfully";
        listCustomers(message);
		
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage="frontend/login.jsp";
	    RequestDispatcher requestDispatcher=request.getRequestDispatcher(loginPage);
	    requestDispatcher.forward(request, response);		
	}

	public void doLogin() throws ServletException, IOException {

		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
	Customer customer=	customerDAO.checkLogin(email, password);

	if(customer==null) {
		String message="Login Failed. Please check your email and password.";
		request.setAttribute("message", message);
		showLogin();
	} else {
		 HttpSession session=request.getSession();
		 session.setAttribute("loggedCustomer", customer);
		// showLogin();
		
		 Object objRedirectURL=session.getAttribute("redirectURL");
		
          if(objRedirectURL!=null) {
        	  String redirectURL= (String) objRedirectURL;
        	  session.removeAttribute("redirectURL");
        	  response.sendRedirect(redirectURL);
          } else {
     		 showCustomerHomePage();
 
          }
		 
	}
}
	
	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage="frontend/customer_profile.jsp";
	    RequestDispatcher dispatcher=request.getRequestDispatcher(profilePage);
	    dispatcher.forward(request, response);	
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		CommonUtility.generateCountryList(request);
		String editPage="frontend/edit_profile.jsp";
	    RequestDispatcher dispatcher=request.getRequestDispatcher(editPage);
	    dispatcher.forward(request, response);			
	}

	
	public void showCustomerHomePage() throws ServletException, IOException {

		CategoryDAO categoryDAO = new CategoryDAO();
		BookDAO bookDAO = new BookDAO();

		List<Category> listCategory = categoryDAO.listAll();
		List<Book> listNewBooks = bookDAO.listNewBooks();
		List<Book> listBestSellingBooks = bookDAO.listBestSellingBooks();
		List<Book> listMostFavoredBooks = bookDAO.listMostFavoredBooks();

		request.setAttribute("listCategory", listCategory);
		request.setAttribute("listNewBooks", listNewBooks);
		request.setAttribute("listBestSellingBooks", listBestSellingBooks);
		request.setAttribute("listMostFavoredBooks", listMostFavoredBooks);

		String homepage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}
	public void updateCustomerProfile() throws ServletException, IOException {
//retrive customer object from session
		Customer customer= (Customer) request.getSession().getAttribute("loggedCustomer");
		updateCustomerFieldsFromForm(customer);
		customerDAO.update(customer);
		showCustomerProfile();
	}

	public void newCustomer() throws ServletException, IOException {
//use java internationalization and localization to display country list
		
	   CommonUtility.generateCountryList(request);
				
		String customerForm="customer_form.jsp";
		request.getRequestDispatcher(customerForm).forward(request, response);
		
	}
/*
	private void generateCountryList() {
		String[] countryCodes=Locale.getISOCountries();
		
		Map<String,String> mapCountries=new TreeMap<>();
		
		for(String countryCode : countryCodes) {
			Locale locale=new Locale("",countryCode);
			String code=locale.getCountry();
			String name=locale.getDisplayCountry();
			
			mapCountries.put(name, code);
		}
		
		request.setAttribute("mapCountries",mapCountries );
	}
	*/
	
	public void  showCustomerRegistrationForm() throws ServletException, IOException {
		
		CommonUtility.generateCountryList(request);

		
		String registerForm="frontend/register_form.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(registerForm);
		dispatcher.forward(request, response);
		
	}

	
	
}
