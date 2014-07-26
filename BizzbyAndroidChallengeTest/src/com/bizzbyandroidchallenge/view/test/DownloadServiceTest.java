package com.bizzbyandroidchallenge.view.test;

import com.bizzbyandroid.service.DownloadService;
import com.bizzbyandroidchallenge.view.CurrenyCodeListActivity.CodeListResultReceiver;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.test.ServiceTestCase;

public class DownloadServiceTest extends ServiceTestCase<DownloadService> {

	private Intent serviceIntent;

	public DownloadServiceTest() {
		super(DownloadService.class);
	}

	public DownloadServiceTest(Class<DownloadService> serviceClass) {
		super(serviceClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();

	}

	public void testHandleIntent() throws InterruptedException {
		serviceIntent = new Intent();
		serviceIntent.putExtra("Receiver", new ResultReceiver(new Handler()));
		serviceIntent.setClass(getContext(), DownloadService.class);
		startService(serviceIntent);
		assertNotNull(serviceIntent);

		// This is done so that Handle intent is called
		Thread.sleep(4000);

	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub

		super.tearDown();
	}

}
