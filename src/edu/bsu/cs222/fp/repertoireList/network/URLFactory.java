package edu.bsu.cs222.fp.repertoireList.network;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;                       

public class URLFactory {
	private final String apiKey;
	
	public URLFactory(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String createURLForSearchTerm(String query) {
		String encodedQuery = "";
		try {
			encodedQuery = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		String url =  "http://developer.echonest.com/api/v4/song/search?api_key=" + apiKey + "&artist=" + encodedQuery + "&format=xml&results=100";
		return url;
	}
}