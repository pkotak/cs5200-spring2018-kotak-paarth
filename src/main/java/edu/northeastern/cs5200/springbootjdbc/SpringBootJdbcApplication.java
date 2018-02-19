package edu.northeastern.cs5200.springbootjdbc;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import model.Developer;
import model.DeveloperDao;
import model.Website;
import model.WebsiteDao;

@SpringBootApplication
public class SpringBootJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcApplication.class, args);
		
//		DeveloperDao devDao = DeveloperDao.getInstance();
//		
//		Developer devFromId = devDao.findDeveloperById(12);
//		System.out.println(devFromId);
//		
//		Developer devFromUsername = devDao.findDeveloperByUsername("bob");
//		System.out.println(devFromUsername);
//		
//		Developer devFromCredentials = devDao.findDeveloperByCredentials("charlie", "charlie");
//		System.out.println(devFromCredentials);
//		
//		Developer developerPique = new Developer();
//		developerPique.setId(777);
//		developerPique.setFirstName("Gerard");
//		developerPique.setLastName("Pique");
//		developerPique.setUsername("pique");
//		developerPique.setPassword("pique");
//		developerPique.setEmail("pq@g.com");
//		developerPique.setDeveloperKey("3445ghkua");
//		System.out.println("Rows affected : "+ devDao.createDeveloper(createDevPique));
//		System.out.println("Rows affected : "+ devDao.updateDeveloper(777, developerPique));
//		System.out.println("Rows affected : "+ devDao.deleteDeveloper(777));
//		List<Developer> developers = devDao.findAllDevelopers();
//		System.out.println(developers);
		
		WebsiteDao websiteDao = WebsiteDao.getInstance();
		
		List<Website> allWebsites = websiteDao.findAllWebsites();
		System.out.println(allWebsites);
		
		List<Website> devWebsites = websiteDao.findWebsitesForDeveloper(12);
		System.out.println(devWebsites);
		
		Website websiteFromId = websiteDao.findWebsiteById(567);
		System.out.println(websiteFromId);
		
		Website googleWebsite = new Website(777, 12, "Google", "More than a search engine", "2018-02-19", "2018-02-19", 500000000);
//		System.out.println("Rows affected : "+ websiteDao.createWebsite(23, googleWebsite));
		
		Website googleWebsiteUpdated = new Website(777, 12, "Google", "Growing into something bigger", "2009-02-19", "2018-02-19", 520000000);
//		System.out.println("Rows affected : "+ websiteDao.updateWebsite(777, googleWebsiteUpdated));
		
		System.out.println("Rows affected : "+ websiteDao.deleteWebsite(777));
	}
}
