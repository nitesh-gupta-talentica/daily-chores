package com.daily.chores.checklist.service;

import java.io.FileInputStream;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.daily.chores.checklist.domain.CheckListModel;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;



public class CheckListService {
	
	public String saveChecklist(CheckListModel checkList) {
		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionCheckList = dbFireStore.collection("checklists").document().set(checkList);
		return null;
	}
	
	public CheckListModel getCheckList(String title) throws InterruptedException, ExecutionException {
		
		try {
			FileInputStream serviceAccount = new FileInputStream("./daily-chores-c75a4-firebase-adminsdk-mt309-e5ff5da233.json");
			
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://daily-chores-c75a4.firebaseio.com")
					.build();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Firestore dbFireStore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFireStore.collection("checkList").document(title);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		
		DocumentSnapshot document = future.get();
		
		CheckListModel checklist = new CheckListModel();
		if(document.exists()) {
			checklist = document.toObject(CheckListModel.class);
			return checklist;
		}
		return null;
		
	}
	

}
