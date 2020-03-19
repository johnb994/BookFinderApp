package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.BookFinder;
import main.GoogleBooksAPI;

	/*
	 
 	BookFinderApp 
 	
 	Requirements:
 	------------
	- User can enter a search query into an input field
	- User can submit the query. This will call an API that will return an array of books with the corresponding data (Title, Author, Published Date, Picture, etc)
	- User can see the list of books appearing on the page
	
	Bonus Features:
	--------------
	- For each item in the list add a link that will send the User to an external site which has more information about the book
	- Implement a Responsive Design
	- Add loading animations
	
	 */

public class BookFinderTest {

	// Variables
	public BookFinder bookFinder;
	public GoogleBooksAPI gbAPI;
	public String input;
	
	@Test
	void shouldCreateBookFinderInstance() {
		
		bookFinder = new BookFinder();
		Assertions.assertNotNull(bookFinder); // asserting creation of instance of BookFinder()
	}
	
	@Test
	void shouldCreateGoogleBooksAPIInstance() {
		input = "Harry Potter";
		gbAPI = new GoogleBooksAPI(input);
		Assertions.assertNotNull(gbAPI); // asserting creation of instance of GoogleBooksAPI()
	}
	
	/*
	 * NOTE:
	 * =====
	 * 
	 * The 2 BELOW test cases may be unnecessary because I block off the
	 * those cases before they reach the GoogleBooksAPI Instance
	 */
	
	//	@Test
	//	void NullStringGoogleBooksAPIInstance() {
	//		input = null;
	//		gbAPI = new GoogleBooksAPI(input);
	//		Assertions.assertNull(gbAPI); // asserting creation of instance of GoogleBooksAPI()
	//	}
	//	
	//	@Test
	//	void EmptyStringGoogleBooksAPIInstance() {
	//		input = "";
	//		gbAPI = new GoogleBooksAPI(input);
	//		Assertions.assertEquals("", input); // asserting creation of instance of GoogleBooksAPI()
	//		
	//	}
	
	
	

}











