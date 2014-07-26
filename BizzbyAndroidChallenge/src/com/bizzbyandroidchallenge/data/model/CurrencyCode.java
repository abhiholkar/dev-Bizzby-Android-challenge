package com.bizzbyandroidchallenge.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Abhi
 * This is data model object. Used as a model for fetched content
 * This class also implements parcelable.-This is done keeping in
 * view for future use, if we would wrap this object inside Intent
 */
public class CurrencyCode implements Parcelable {

	/**Short form form for the code	 */
	private String currencycodeSF;
	
	/**Long form for the code */
	private String currencyCodeLF;

	/** CTOR */
	public CurrencyCode(){
		
	}
	/**
	 * This CTOR is for creating a parcelable form 
	 * Note : this is used when wrapping Currencycodes inside Intent
	 * @param source
	 */
	public CurrencyCode(Parcel source) { 
		readFromParcel(source);
	}

	/**
	 * @param source
	 */
	private void readFromParcel(Parcel source) {
	
		currencycodeSF = source.readString();
		currencyCodeLF = source.readString();
		
	}

	/**
	 * @return String : Currency code long form
	 */
	public String getCurrencyCodeLF() {
		return currencyCodeLF;
	}

	/**
	 * Sets currencyCode Long form
	 * @param currencyCodeLF 
	 */
	public void setCurrencyCodeLF(String currencyCodeLF) {
		this.currencyCodeLF = currencyCodeLF;
	}

	/**
	 * @return String : Currency Code short form
	 */
	public String getCurrencycodeSF() {
		return currencycodeSF;
	}

	/**
	 * Sets currency code Short form
	 * @param currencycodeSF 
	 */
	public void setCurrencycodeSF(String currencycodeSF) {
		this.currencycodeSF = currencycodeSF;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

		dest.writeString(currencycodeSF);
		dest.writeString(currencyCodeLF);
	}

	public static final Parcelable.Creator<CurrencyCode> CREATOR = new Parcelable.Creator<CurrencyCode>() {

		@Override
		public CurrencyCode createFromParcel(Parcel source) {
			return new CurrencyCode(source);
		}

		@Override
		public CurrencyCode[] newArray(int size) {
			return new CurrencyCode[size];
		}
	};

}
