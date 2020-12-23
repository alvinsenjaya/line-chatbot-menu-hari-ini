package com.alvinsenjaya.menuhariini.service;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alvinsenjaya.menuhariini.dto.MenuDetailResponse;
import com.alvinsenjaya.menuhariini.dto.MenuOverviewListResponse;
import com.alvinsenjaya.menuhariini.model.MenuDetail;
import com.alvinsenjaya.menuhariini.model.MenuOverview;

@Service
public class MenuHariIniService {
	String url = "https://masak-apa.tomorisakura.vercel.app";
	
	private RestTemplate restTemplate;
	  
	public MenuHariIniService(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();
	}
	
	public List<MenuOverview> findMenuOverviewByTitle(String title) {
		try {
			MenuOverviewListResponse menuOverviewListResponse = restTemplate.getForObject(url + "/api/search/?q=" + title, MenuOverviewListResponse.class);
			return menuOverviewListResponse.getResults();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	public MenuDetail findMenuDetailByKey(String key) {
		try {
			MenuDetailResponse menuDetailResponse = restTemplate.getForObject(url + "/api/recipe/" + key, MenuDetailResponse.class);
			return menuDetailResponse.getResults();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
