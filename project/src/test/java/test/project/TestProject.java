package test.project;

import javax.persistence.Persistence;

public class TestProject {
	
	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("project");
		
	}

}
