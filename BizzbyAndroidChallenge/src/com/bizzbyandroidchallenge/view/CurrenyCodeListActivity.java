package com.bizzbyandroidchallenge.view;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bizzbyandroid.service.DownloadService;
import com.bizzbyandroidchallenge.adapter.CurrencyCodeAdapter;
import com.bizzbyandroidchallenge.appstates.ApplicationStates;
import com.bizzbyandroidchallenge.controller.AppController;
import com.bizzbyandroidchallenge.data.model.CurrencyCode;

/**
 * @author Abhi Rate list : Fetches content and displays the result in list view
 */
public class CurrenyCodeListActivity extends Activity {

	/** Progess Dialog while fecthing content */
	private ProgressDialog pdialog;

	/** List view */
	private ListView mListView;
	
	/** Text view  */
	private TextView mTextView;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate_list);

		mListView = (ListView) findViewById(R.id.currencycodelist);
		
		mTextView = (TextView)findViewById(android.R.id.empty);
		
		

		if (pdialog == null) {

			pdialog = new ProgressDialog(this);
			pdialog.setTitle(getResources().getString(R.string.dialog_title));
			pdialog.setMessage(getResources().getString(R.string.dialog_text));
			pdialog.setCancelable(false);
		}

		mListView.setVisibility(View.INVISIBLE);
		pdialog.show();

	}

	@Override
	protected void onPause() {

		super.onPause();
	}

	@Override
	protected void onResume() {

		super.onResume();
		
		if (!DownloadService.isRunning()) {
			Intent serviceIntent = new Intent(this, DownloadService.class);
			serviceIntent.putExtra("Receiver", new CodeListResultReceiver(
					new Handler()));
			startService(serviceIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rate_list, menu);
		return true;
	}

	public class CodeListResultReceiver extends ResultReceiver {

		public CodeListResultReceiver(Handler handler) {
			super(handler);
			
		}

		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {

			pdialog.setMessage("");
			switch (resultCode) {

			case ApplicationStates.DOWNLOAD_STARTED:
				pdialog.setMessage(getResources().getString(R.string.dialog_text_started));
				break;

			case ApplicationStates.DOWNLOAD_DATA_FETCHED:
				pdialog.setMessage(getResources().getString(R.string.dialog_text_fetched));
				break;

			case ApplicationStates.DOWNLOAD_DATA_PARSED:
				pdialog.setMessage(getResources().getString(R.string.dialog_text_parsed));
				break;

			case ApplicationStates.DOWNLOAD_DATA_BINDING:
				pdialog.setMessage(getResources().getString(R.string.dialog_text_bind));

				break;
				
			case ApplicationStates.DOWNLOAD_DATA_FAILED:
				pdialog.setMessage(getResources().getString(R.string.dialog_text_failed));
				pdialog.dismiss();  
				
				mTextView.setText(R.string.list_empty_text);
				mTextView.setVisibility(View.VISIBLE);

				break;

			case ApplicationStates.DOWNLOAD_FINISHED:

				pdialog.setMessage(getResources().getString(R.string.dialog_text_finished));
				pdialog.dismiss();  
				List<CurrencyCode> codeList = AppController.getAppControllerInstance().getCurrencyCodeList();

				CurrencyCodeAdapter adapter = new CurrencyCodeAdapter(getApplicationContext(),codeList);
				mListView.setAdapter(adapter);
			    mListView.setVisibility(View.VISIBLE); 

				break;
			}
		}

	}

}
