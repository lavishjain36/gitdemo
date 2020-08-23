package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	    private EntityManager entityManager;
		private BookDAO bookDAO;
		private CategoryDAO categoryDAO;
		private HttpServletRequest request;
		private HttpServletResponse response;
		
		public BookServices(  HttpServletRequest request, HttpServletResponse response) {
			super();
			this.request = request;
			this.response = response;
			bookDAO=new BookDAO();
			categoryDAO=new CategoryDAO();
		}
	
public void listBooks()throws ServletException, IOException {
		listBooks(null);
		}
		
	public void listBooks(String message)throws ServletException, IOException {
		
	List<Book> listBooks=bookDAO.listAll();
	request.setAttribute("listBooks", listBooks);
	
	if(message!=null) {
		request.setAttribute("message", message);
	}
	
	String listPage="book_list.jsp";
	RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
	requestDispatcher.forward(request, response);
	
	}

	public void showBookNewForm() throws ServletException, IOException {
		List<Category> listCategory=categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String newPage="book_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(newPage);
		
		
		requestDispatcher.forward(request, response);
	}

	public void createBook() throws ServletException, IOException {

		Integer categoryId=Integer.parseInt(request.getParameter("category"));
		String title=request.getParameter("title");
		
		Book existBook=bookDAO.findByTitle(title);
		
		if(existBook!=null) {
			String message="Could not create new book because the title " 
		                       +title + " already exist.";
			listBooks(message);
			return;
		}
		
		Book newBook=new Book();
		readBookFields(newBook);
	
				
		
		/*
		
		String author=request.getParameter("author");
		String description=request.getParameter("description");
		String isbn=request.getParameter("isbn");
		float price=Float.parseFloat(request.getParameter("price"));
		
 DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
 Date publishDate=null;
 
 try {
	 publishDate=dateFormat.parse(request.getParameter("publishDate"));
} catch (ParseException ex) {

	ex.printStackTrace();
	throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
}
            System.out.println("Category ID: " + categoryId);
            System.out.println("title: " + title);
            System.out.println("author: " + author);
            System.out.println("description: " + description);
            System.out.println("isbn: " + isbn);
            System.out.println("price: " + price);
            System.out.println("publishDate: " + publishDate);
            
           
            
            Book newBook=new Book();
            newBook.setTitle(title);
            newBook.setAuthor(author);
            newBook.setDescription(description);
            newBook.setIsbn(isbn);
            newBook.setPrice(price);
            newBook.setPublishDate(publishDate);
          
            
            Category category=categoryDAO.get(categoryId);
			newBook.setCategory(category);
            
            
            
            
            
            
            
            
            //code to read the data of a file uploaded in the form into an array of bytes

            Part part=request.getPart("bookImage");
            
            if(part!=null && part.getSize()>0) {
            	long size=part.getSize();
            	byte[] imageBytes=new byte[(int) size];
            	
            	InputStream inputStream=part.getInputStream();
            	inputStream.read(imageBytes);
            	inputStream.close();
            	
            	newBook.setImage(imageBytes);
            	
            }
         */   
            Book createdBook=bookDAO.create(newBook);
            
            if(createdBook.getBookId()>0)
            {
            	String message="A new book user has been created successfully.";
          request.setAttribute("message",message);
          listBooks(message);
            
            }
            		
            

	}
	
	public void readBookFields(Book book) throws IOException, ServletException {
		String title=request.getParameter("title");
        String author=request.getParameter("author");
		String description=request.getParameter("description");
		String isbn=request.getParameter("isbn");
		float price=Float.parseFloat(request.getParameter("price"));
		
       DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
       Date publishDate=null;
 
 try {
	 publishDate=dateFormat.parse(request.getParameter("publishDate"));
} catch (ParseException ex) {

	ex.printStackTrace();
	throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
}
 
 book.setTitle(title);
 book.setAuthor(author);
 book.setDescription(description);
 book.setIsbn(isbn);
 //book.setPrice(price);
 book.setPublishDate(publishDate);

 Integer categoryId=Integer.parseInt(request.getParameter("category"));
 Category category=categoryDAO.get(categoryId);
 book.setCategory(category);
 
 book.setPrice(price);
	
	   Part part=request.getPart("bookImage");
       
       if(part!=null && part.getSize()>0) {
       	long size=part.getSize();
       	byte[] imageBytes=new byte[(int) size];
       	
       	InputStream inputStream=part.getInputStream();
       	inputStream.read(imageBytes);
       	inputStream.close();
       	
       	book.setImage(imageBytes);
       	
       }
		
	}

	public void editBook() throws ServletException, IOException {

		Integer bookId=Integer.parseInt(request.getParameter("id"));
		Book book=bookDAO.get(bookId);
		List<Category> listCategory=categoryDAO.listAll();
		
		
		
		request.setAttribute("book", book);
		request.setAttribute("listCategory", listCategory);

	
		String editPage="book_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
		
		
		requestDispatcher.forward(request, response);
		
	}

	public void updateBook() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("bookId"));
		String title=request.getParameter("title");
		
		Book existBook=bookDAO.get(bookId);
		Book bookByTitle=bookDAO.findByTitle(title);
		
		if(bookByTitle!=null && !existBook.equals(bookByTitle)) {
			
			String message="Could not update book because there's another book having same title.";
			listBooks(message);
			return ;
			
		}
		
		readBookFields(existBook);
		
		bookDAO.update(existBook);
		
		String message="The book has been updated successfully.";
		listBooks(message);
	}

	public void deleteBook() throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("id"));

		bookDAO.delete(bookId);
		
		String message="The book has been deleted successfully.";
		listBooks(message);
		
	}

	public void listBooksByCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("id"));
         List<Book> listBooks=bookDAO.listByCategory(categoryId);
         Category  category= categoryDAO.get(categoryId);
         
         
         request.setAttribute("listBooks", listBooks);
         request.setAttribute("category", category);

         
         String listPage="frontend/books_list_by_category.jsp";
 		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
 		
 		
 		requestDispatcher.forward(request, response);
		
	}

	public void viewBookDetail() throws ServletException, IOException {

		Integer bookId=Integer.parseInt(request.getParameter("id"));
        Book book=bookDAO.get(bookId);
      //  List<Category> listCategory=categoryDAO.listAll();
        
     //   request.setAttribute("listCategory", listCategory);
        request.setAttribute("book", book);
		
        String detailPage="frontend/book_detail.jsp";
 		RequestDispatcher requestDispatcher=request.getRequestDispatcher(detailPage);
 		
 		
 		requestDispatcher.forward(request, response);
	}

	public void search() throws ServletException, IOException {

		String keyword=request.getParameter("keyword");
		List<Book> result=null;
		
		if(keyword.equals("")) {
			result=bookDAO.listAll();
			
		}else {
			result=bookDAO.search(keyword);

		}
		  request.setAttribute("keyword", keyword);
		  request.setAttribute("result", result);

			
		  
	        String resultPage="frontend/search_result.jsp";
	 		RequestDispatcher requestDispatcher=request.getRequestDispatcher(resultPage);
	 		
	 		
	 		requestDispatcher.forward(request, response);
		
		
	}

	
	
}

