package com.bizzbyandroid.service;

import java.util.List;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;

import com.bizzbyandroidchallenge.appstates.ApplicationStates;
import com.bizzbyandroidchallenge.controller.AppController;
import com.bizzbyandroidchallenge.data.model.CurrencyCode;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * 
 */
public class DownloadService extends IntentService {

	/** To check whether service is already running */
	private static boolean isRunning;

	private static String serviceName = "DownloadService";

	public DownloadService() {
		super(serviceName);
	}

	@Override
	public void onCreate() {

		super.onCreate();
		isRunning = true;
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
		isRunning = false;
	}

	/* (non-Javadoc)
	 * @see android.app.IntentService#onStartCommand(android.content.Intent, int, int)
	 * retrun START_REDELIVER_INTENT - So that intent could be redelivered if failed
	 * by any chance 
	 */ 
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		super.onStartCommand(intent, flags, startId);
		return START_REDELIVER_INTENT;
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		if (intent != null) {

			ResultReceiver receiver;
			receiver = (ResultReceiver) intent.getParcelableExtra("Receiver");

			try {

				receiver.send(ApplicationStates.DOWNLOAD_STARTED, null);

				String jsonResponse = AppController.getAppControllerInstance()
						.fetchCurrencies();

				if (jsonResponse != null) {

					receiver.send(ApplicationStates.DOWNLOAD_DATA_FETCHED, null);

					List<CurrencyCode> codeList = AppController
							.getAppControllerInstance()
							.parseJSONtoCurrencyCodeList(jsonResponse);

					receiver.send(ApplicationStates.DOWNLOAD_DATA_PARSED, null);

					AppController.getAppControllerInstance()
							.setCurrencyCodeList(codeList);

					receiver.send(ApplicationStates.DOWNLOAD_DATA_BINDING, null);
				} else {

					receiver.send(ApplicationStates.DOWNLOAD_DATA_FAILED, null);
					 return;

				}

				receiver.send(ApplicationStates.DOWNLOAD_FINISHED, null);

			} catch (Exception e) {

				receiver.send(ApplicationStates.DOWNLOAD_DATA_FAILED, null);

			}
		}
	}

	public static boolean isRunning() {
		return isRunning;
	}

}
