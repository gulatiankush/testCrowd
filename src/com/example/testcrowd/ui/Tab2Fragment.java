package com.example.testcrowd.ui;

import com.example.testcrowd.R;
import com.example.testcrowd.R.layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Tab2Fragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return (LinearLayout) inflater.inflate(R.layout.tab2, container, false);
	}

}
