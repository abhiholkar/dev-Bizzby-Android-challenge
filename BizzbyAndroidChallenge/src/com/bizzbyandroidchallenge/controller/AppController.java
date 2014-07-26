package com.bizzbyandroidchallenge.controller;

import java.io.IOException;
import java.util.List;

import com.bizzbyandroidchallenge.data.model.CountryCodeList;
import com.bizzbyandroidchallenge.data.model.CurrencyCode;
import com.bizzbyandroidchallenge.network.NetworkManager;

/**
 * @author Abhi Main controller for the app..
 */
public class AppController {

	/**Singleton	 */
	public static AppController instance;
	
	/**Network Manager instance	 */
	private NetworkManager networkManager;

	/** private CTOR */
	private AppController() { 
	
	}

	/**
	 * @return AppController instance
	 */
	public static AppController getAppControllerInstance() {
		if (instance == null) {
			instance = new AppController();
		}
		return instance;
	}
	
	/**
	 * @return String : JSON response
	 * @throws IOException
	 */
	public String fetchCurrencies() throws IOException{
		
		networkManager = NetworkManager.getNetworkManagerInstance();
		return networkManager.fetchCurrencies();
		
	}
	
	/**
	 * @param json
	 * @return
	 */
	public List<CurrencyCode> parseJSONtoCurrencyCodeList(String json){
		return networkManager.parseJSONtoCurrencyCodeList(json);
	} 
	
	/**
	 * sets the List of currency codes
	 * @param List <>codeList
	 */
	public void setCurrencyCodeList(List<CurrencyCode> codeList){
		CountryCodeList.setCodeList(codeList);
	}
	
	/**
	 * @return List
	 */
	public List<CurrencyCode> getCurrencyCodeList(){
		return CountryCodeList.getCodeList();
		
	}

}
