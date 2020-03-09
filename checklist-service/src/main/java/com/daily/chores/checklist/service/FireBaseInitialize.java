package com.daily.chores.checklist.service;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FireBaseInitialize {
	
	@PostConstruct
	public void initialize() {
		
		try {
			FileInputStream serviceAccount = new FileInputStream("/src/main/resources/daily-chores-c75a4-firebase-adminsdk-mt309-e5ff5da233.json");
			
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://daily-chores-c75a4.firebaseio.com")
					.build();
			FirebaseApp.initializeApp(options);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
