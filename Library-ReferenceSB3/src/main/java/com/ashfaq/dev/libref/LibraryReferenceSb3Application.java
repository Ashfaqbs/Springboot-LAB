package com.ashfaq.dev.libref;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryReferenceSb3Application {

	public static void main(String[] args) {
		SpringApplication.run(LibraryReferenceSb3Application.class, args);
		try {
            // Connect to the website
            Document document = Jsoup.connect("https://github.com/Ashfaqbs")
                   .timeout(3000)
                   .get();

            // Find all article titles
            Elements articles = document.select(".article-title");

            // Print each article title
            for (Element article : articles) {
                System.out.println(article.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
