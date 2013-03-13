package com.example.testcrowd.ui;

import java.io.InputStream;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.StrictMode;

import com.example.testcrowd.R;
import com.example.testcrowd.services.WebServices;

//final AlertDialog.Builder adb = new AlertDialog.Builder(this);
//		final Button postButton = (Button) findViewById(R.id.postButton);
//		final EditText editTextQuestion = (EditText) findViewById(R.id.editText_question);
//
//		postButton.setOnClickListener(new Button.OnClickListener() {
//			public void onClick(View v) {
//				try {
//					// Perform action on click
//					String question = editTextQuestion.getText().toString();
//					// question = question.replace(' ', '+');
//
//				} catch (Exception e) {
//					AlertDialog ad = adb.create();
//					ad.setMessage("Failed to Launch");
//					ad.show();
//
//				}
//			}
// });

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		String label1 = getResources().getString(R.string.label1);
		Tab tab = actionBar.newTab();
		tab.setText(label1);
		TabListener<Tab1Fragment> tl = new TabListener<Tab1Fragment>(this,
				label1, Tab1Fragment.class);
		tab.setTabListener(tl);
		actionBar.addTab(tab);

		String label2 = getResources().getString(R.string.label2);
		tab = actionBar.newTab();
		tab.setText(label2);
		TabListener<Tab2Fragment> tl2 = new TabListener<Tab2Fragment>(this,
				label2, Tab2Fragment.class);
		tab.setTabListener(tl2);
		actionBar.addTab(tab);

		InputStream in = WebServices.callService();

		// InputStream in = WebServices
		// .createUser("{ \"firstName\":\"James\" , \"lastName\":\"Jerry\" }");
		String result = WebServices.convertStreamToString(in);
		result = result.toString();
	}

	private class TabListener<T extends Fragment> implements
			ActionBar.TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		/**
		 * Constructor used each time a new tab is created.
		 * 
		 * @param activity
		 *            The host Activity, used to instantiate the fragment
		 * @param tag
		 *            The identifier tag for the fragment
		 * @param clz
		 *            The fragment's Class, used to instantiate the fragment
		 */
		public TabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Check if the fragment is already initialized
			if (mFragment == null) {
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				// If it exists, simply attach it in order to show it
				ft.attach(mFragment);
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				// Detach the fragment, because another one is being attached
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// User selected the already selected tab. Usually do nothing.
		}
	}

}
