package com.example.testcrowd.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class WebServices {
	// private static final String IPAddress = "http://192.168.2.2:8080";

	public static InputStream callService() {

		InputStream in = null;

		try {
			URL url = new URL("http://192.168.2.5:8080"
					+ "/testCroud/rest/user");
			// URL url = new URL("https://"
			// + java.net.InetAddress.getLocalHost().getHostAddress()
			// + "/testCroud/rest/user");

			// URL url = new URL(IPAddress + "/testRest/rest/hello");
			URLConnection conn = url.openConnection();

			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod("GET");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.addRequestProperty("id", "50e152390364a9eb114e64b0");

			httpConn.connect();

			DataOutputStream dataStream = new DataOutputStream(
					conn.getOutputStream());

			// dataStream.writeBytes(text);
			dataStream.flush();
			dataStream.close();

			int responseCode = httpConn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return in;
	}

	public static InputStream createUser(String inputJSON) {// TODO Temp input,
															// use
		// makeUser to verify
		// and create a json
		// string input {
		InputStream in = null;

		try {
			URL url = new URL("http://192.168.2.5:8080"
					+ "/testCroud/rest/user");
			// URL url = new URL(IPAddress + "/testRest/rest/users");
			URLConnection conn = url.openConnection();

			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod("POST");

			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.connect();

			DataOutputStream dataStream = new DataOutputStream(
					conn.getOutputStream());

			dataStream.writeBytes(makeUser().toString());

			dataStream.flush();
			dataStream.close();

			int responseCode = httpConn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
			// handle response - success(200) or failure(400s)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	private static JSONObject makeUser() {
		// TODO put values from text fields input data
		JSONObject object = new JSONObject();
		try {
			object.put("name", "Jack Hack");
			object.put("score", 200);
			object.put("current", 152.32);
			object.put("nickname", "Hacker");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object;
	}
}
