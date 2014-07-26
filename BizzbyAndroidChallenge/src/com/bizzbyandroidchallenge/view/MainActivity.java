package com.bizzbyandroidchallenge.view;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	/**
	 * List view for main menu
	 */
	private ListView mListView;

	/* For debug strings */
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Used For testing the unit test cases
		// Comment out below call on release code
		disablekeyGuard();

		mListView = (ListView) findViewById(R.id.mainlistview);

		String[] items = getResources().getStringArray(R.array.menu_items);

		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		mListView.setAdapter(listAdapter);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String item = (String) mListView.getItemAtPosition(position);
				Log.d(TAG, "Item clicked is : " + item);

				if (item.equals(getResources().getString(
						R.string.menu_item_codelist))) {
					Intent intent = new Intent(getApplicationContext(),
							CurrenyCodeListActivity.class);
					startActivity(intent);
				}

				if (item.equals(getResources().getString(
						R.string.menu_item_about))) {
					Intent intent = new Intent(getApplicationContext(),
							AboutActivity.class);
					startActivity(intent);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**This method is used only to ensure test cases are tested	 */
	@SuppressWarnings("deprecation")
	private void disablekeyGuard() {
		KeyguardManager mKeyGuardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
		KeyguardManager.KeyguardLock mLock = mKeyGuardManager
				.newKeyguardLock("MainActivity");
		mLock.disableKeyguard();
	}

}
