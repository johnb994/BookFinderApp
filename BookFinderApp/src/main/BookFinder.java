package main;

import java.io.IOException;
import java.util.Scanner;


public class BookFinder {

	public String input;
	public String searchQuery;
	public GoogleBooksAPI gbAPI;
	
	public BookFinder() {
		
		input = scannerInput();
		
		if (input.isBlank()) {
			System.out.println("searchQuery is empty or blank");
			input = scannerInput();
		} else if (input.equals(null)) {
			System.out.println("searchQuery is null");
			input = scannerInput();
		} else {
			setSearchQuery(input);
			gbAPI = new GoogleBooksAPI(getSearchQuery());
		}
		
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	public String scannerInput() { // Converted Scanner to method because of repeated use
		String result;
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter your search query");
		result = in.nextLine();
		in.close();
		
		return result;
		
	}
}
