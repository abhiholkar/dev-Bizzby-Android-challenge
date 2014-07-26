package com.bizzbyandroidchallenge.view.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bizzbyandroidchallenge.view.AboutActivity;
import com.bizzbyandroidchallenge.view.CurrenyCodeListActivity;
import com.bizzbyandroidchallenge.view.MainActivity;
import com.bizzbyandroidchallenge.view.R;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private ListView mListView;

	private ArrayAdapter<String> adapter;

	private MainActivity mActivity;

	private TextView mTextView;

	private static final int ADAPTER_COUNT = 2;

	public static final int INITIAL_POSITION = 0;
	
	private static final int ABOUT_TEST_POSITION=1;

	public MainActivityTest() {
		super(MainActivity.class);

	}

	@Override
	protected void setUp() throws Exception {
		
		super.setUp();

		setActivityInitialTouchMode(false);

		mActivity = getActivity();

		mListView = (ListView) mActivity.findViewById(R.id.mainlistview);

		adapter = (ArrayAdapter<String>) mListView.getAdapter();

		mTextView = (TextView) mActivity.findViewById(android.R.id.text1);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	public void testPreConditions() {
		assertTrue("ListView is null", mListView != null);
		assertTrue("List Adapter is null", adapter != null);
		assertEquals(adapter.getCount(), ADAPTER_COUNT);
		assertNotNull("TextView is null", mTextView);
	}

	public void testStartCurrenyCodeListActivity() {

		ActivityMonitor monitor = getInstrumentation().addMonitor(
				CurrenyCodeListActivity.class.getName(), null, false);

		mActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				mListView.requestFocus();

				mListView.setSelection(INITIAL_POSITION);

			}
		});
		getInstrumentation().waitForIdleSync();

		TouchUtils.clickView(this, mListView.getSelectedView());

		CurrenyCodeListActivity newActivity = (CurrenyCodeListActivity) monitor
				.waitForActivityWithTimeout(5000);

		assertNotNull("CurrenyCodeListActivity is null", newActivity); 
		
		getInstrumentation().removeMonitor(monitor);

	}
	
	public void testAboutActivity() {

		ActivityMonitor monitor = getInstrumentation().addMonitor(
				AboutActivity.class.getName(), null, false);

		mActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				

				mListView.requestFocus();

				mListView.setSelection(ABOUT_TEST_POSITION);
				
				
				mListView.performItemClick(mListView.getSelectedView(), ABOUT_TEST_POSITION, mListView.getItemIdAtPosition(ABOUT_TEST_POSITION));
				
			

			}
		});
		getInstrumentation().waitForIdleSync();  
		

		AboutActivity aboutActivity = (AboutActivity) monitor
				.waitForActivityWithTimeout(5000);
		
		
		assertNotNull("About Activity is null", aboutActivity);

		
		sendKeys(KeyEvent.KEYCODE_BACK);

		TouchUtils.clickView(this, mListView);

		getInstrumentation().removeMonitor(monitor);

	}

}
