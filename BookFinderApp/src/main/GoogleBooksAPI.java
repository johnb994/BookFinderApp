package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GoogleBooksAPI {

	// Constructor - Accepts String
	public GoogleBooksAPI(String searchQuery) {
		
		String readLine = null;
		
		// Variables for parsing searchQuery if multiple words present
		String delims = "[ ]+";
		String[] tokens = searchQuery.split(delims);
		String formatQuery = "";
		String finalQuery = null;
		
		for(String token : tokens) {
			System.out.println("Token = " + token); // parsing String input from user for Google Books API call
			formatQuery += token + "+"; 
		}
		
		if(formatQuery.charAt(formatQuery.length() - 1) == '+') {
			finalQuery = removeLastCharacter(formatQuery); // Removing last character if it is a plus from API call formatting
		}
		
		// Get Request for Search Querying
		try {
			
			URL getMethodURL = new URL("https://www.googleapis.com/books/v1/volumes?q=" + finalQuery);
			HttpURLConnection connection = (HttpURLConnection) getMethodURL.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			System.out.println("HttpResponseCode = " + responseCode);
			
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				
				JSONParser jsonParse = new JSONParser();
				try {
					JSONObject jobj = (JSONObject) jsonParse.parse(in);
					JSONArray jsonArray = (JSONArray) jobj.get("items"); // Nesting with items {}
					
					System.out.println("Elements Under 'items':");
					System.out.println("Elements under 'volumeInfo':");
					for (int i = 0; i < jsonArray.size(); i++) {
						
						JSONObject jobjIter  = (JSONObject) jsonArray.get(i);
						JSONObject jobjVI = (JSONObject) jobjIter.get("volumeInfo"); // Nesting with volumeInfo {}
						
						System.out.print((i + 1) + ") Title: " + jobjVI.get("title")); // Title of Book
						
						JSONArray jsonISBN = (JSONArray) jobjVI.get("industryIdentifiers"); // Iterating through industryIdentifiers
						for(int j = 0; j < jsonISBN.size(); j++) {
							JSONObject jobjISBN = (JSONObject) jsonISBN.get(j);

							if(jobjISBN.get("type").equals("ISBN_13")) { // Filtering and displaying only ISBN_13 values
								System.out.print(", ISBN: " + jobjISBN.get("identifier"));
							}
						}
						
						JSONArray jsonAuthors = (JSONArray) jobjVI.get("authors"); // Iterating through Authors Array
						if (jsonAuthors != null) { // Only trigger condition if JSONArray is not null
							
							String authObj = "";
							System.out.print(", Author: ");
							
							for (int k = 0; k < jsonAuthors.size(); k++) {
								authObj = (String) jsonAuthors.get(k);
								authObj += ", "; // Formatting for multiple authors if present
								System.out.print(authObj);
							}
							
							System.out.print("Published Date: " + jobjVI.get("publishedDate")); // Published Dated
						}

						System.out.println(", Link: " + jobjVI.get("infoLink")); // Link to Book for more information
						
						/*
						 * Return Format:
						 * 
						 * [Title, ISBN, Author, Published Date, Link, (Picture)]
						 * 
						 * (Picture) to be displayed when GUI is made
						 * 
						 */
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				in.close();
				
				
			} else {
				System.out.println("GET Method Unsuccesful.");
			}
			
		} catch (MalformedURLException e) {
			System.err.println("MalformedException: ");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException: ");
			e.printStackTrace();
		}
	}
	
	public static String removeLastCharacter(String str) {
		   String result = null;
		   if ((str != null) && (str.length() > 0)) {
		      result = str.substring(0, str.length() - 1);
		   }
		   return result;
		}
	
}
