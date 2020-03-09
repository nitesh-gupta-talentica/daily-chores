package com.daily.chores.checklist.service.main;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.chores.checklist.domain.CheckListModel;
import com.daily.chores.checklist.service.CheckListService;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@EnableDiscoveryClient
@RestController
@RequestMapping("/checklists")
@SpringBootApplication
public class ChecklistServiceApplication {
	
//	@Autowired
//	CheckListService checkListService;

	public static void main(String[] args) {
		
		initializeFirebase();
		SpringApplication.run(ChecklistServiceApplication.class, args);
	}

	
	@GetMapping("")
	public List<CheckListModel> getAllCheckLists() throws InterruptedException, ExecutionException {
		System.out.println("id in getall not needed " );
		CheckListService checkListService = new CheckListService();
		List<CheckListModel> checklists =getAllCheckListDetails();
		
		return checklists;
	}
	@GetMapping("/{id}")
	public CheckListModel getCheckList(@PathVariable String id) throws InterruptedException, ExecutionException {
		System.out.println("id is " + id);
		CheckListService checkListService = new CheckListService();
		
		CheckListModel checklist =getCheckListDetails(id);
		
		return checklist;
	}
	
	
	
	private List<CheckListModel> getAllCheckListDetails() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		Firestore dbFireStore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> documentRef = dbFireStore.collection("checklists").get();
		List<QueryDocumentSnapshot> future = documentRef.get().getDocuments();
		
		
		
			List<CheckListModel> checklists = new ArrayList<>();
			//List<QueryDocumentSnapshot> snapshots = doc.getDocuments();
			System.out.println(future.size());
			for(DocumentSnapshot docSnap : future ) {
				System.out.println(docSnap.getId());
				checklists.add(docSnap.toObject(CheckListModel.class));
				
			}
			return checklists;
		//return null;
	}

	@PostMapping("")
	public String addCheckList(@RequestBody CheckListModel checkList) throws InterruptedException, ExecutionException {
		System.out.println("Psot");
		CheckListService checkListService2 = new CheckListService();
	return 	saveChecklist(checkList);
		
		
	}
	@PutMapping("/{id}")
	public String updateCheckList(@PathVariable String id,@RequestBody CheckListModel checkList) throws InterruptedException, ExecutionException {
		System.out.println("Psot");
		CheckListService checkListService2 = new CheckListService();
	return 	updateChecklist(id, checkList);
		
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteCheckList(@PathVariable String id) throws InterruptedException, ExecutionException {
		System.out.println("Psot");
	
		deleteChecklist(id );
		
		
	}
	
private void deleteChecklist(String id) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
	Firestore dbFireStore = FirestoreClient.getFirestore();
	ApiFuture<WriteResult> collectionCheckList = dbFireStore.collection("checklists").document(id).delete();
	
	}


private String updateChecklist(String id, CheckListModel checkList) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
	Firestore dbFireStore = FirestoreClient.getFirestore();
	ApiFuture<WriteResult> collectionCheckList = dbFireStore.collection("checklists").document(id).set(checkList);
	return collectionCheckList.get().toString();
		//return null;
	}


public CheckListModel getCheckListDetails(String title) throws InterruptedException, ExecutionException {
		
	
		Firestore dbFireStore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFireStore.collection("checklists").document(title);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		
		DocumentSnapshot document = future.get();
		
		CheckListModel checklist = new CheckListModel();
		if(document.exists()) {
			checklist = document.toObject(CheckListModel.class);
			return checklist;
		}
		return null;
		
	}

public String saveChecklist(CheckListModel checkList) throws InterruptedException, ExecutionException {
	
	Firestore dbFireStore = FirestoreClient.getFirestore();
	ApiFuture<WriteResult> collectionCheckList = dbFireStore.collection("checklists").document().set(checkList);
	return collectionCheckList.get().toString();
}

    
   public static void initializeFirebase() {
	   try {
			FileInputStream serviceAccount = new FileInputStream("./daily-chores-c75a4-firebase-adminsdk-mt309-e5ff5da233.json");
			
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
