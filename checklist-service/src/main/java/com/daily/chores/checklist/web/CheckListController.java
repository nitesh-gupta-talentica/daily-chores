package com.daily.chores.checklist.web;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.chores.checklist.domain.CheckListModel;
import com.daily.chores.checklist.service.CheckListService;


public class CheckListController {
	

	CheckListService checkListService;
	
	@PostMapping("/")
	public String addCheckList(@RequestBody CheckListModel checkList) {
		checkListService.saveChecklist(checkList);
		
		return null;
	}
	
	@GetMapping("/{id}")
	public String getCheckList(@PathVariable String id) throws InterruptedException, ExecutionException {
		System.out.println("id is " + id);
		CheckListModel checklist =checkListService.getCheckList(id);
		
		return id;
	}

}
