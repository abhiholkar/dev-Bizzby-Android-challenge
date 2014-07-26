package com.bizzbyandroidchallenge.data.model;
import java.util.List;



/**
 * @author Abhi
 *Contianer Class for Country currencycode list 
 */
public class CountryCodeList {
	
	/**List of Currency Code	 */
	private  static List<CurrencyCode> codeList;

	/**
	 * @return List Currency Code
	 */
	public static List<CurrencyCode> getCodeList() {
		return codeList;
	}

	/**
	 * @param codeList
	 */
	public static void setCodeList(List<CurrencyCode> codeList) {
		CountryCodeList.codeList = codeList;
	}

}
