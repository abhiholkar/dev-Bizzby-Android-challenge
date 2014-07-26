package com.bizzbyandroidchallenge.appstates;

/**
 * @author Abhi This maintains the state machine while data is being fetched
 *         asynchronously.
 * 
 */
public class ApplicationStates {

	public static final int DOWNLOAD_STARTED = 0;
	public static final int DOWNLOAD_FINISHED = 1;
	public static final int DOWNLOAD_DATA_FETCHED = 2;
	public static final int DOWNLOAD_DATA_PARSED = 3; 
	public static final int DOWNLOAD_DATA_BINDING = 4;
	public static final int DOWNLOAD_DATA_FAILED = 5;
}
