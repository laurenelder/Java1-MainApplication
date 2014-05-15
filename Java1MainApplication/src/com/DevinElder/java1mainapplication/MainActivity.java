// Devin "Lauren" Elder
// Java 1 Week 2 Project

package com.DevinElder.java1mainapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.DevinElder.java1mainapplication.classData.*;
import com.DevinElder.java1mainapplication.classData.Cities.timeZone;

import org.json.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		getJson();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	// Get and Parse JSON Function
	public void getJson() {
		
		//Get Data
		StringBuffer jsonBuffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(getAssets().open(
			"data.json")));
			String tempData;
			while ((tempData = reader.readLine()) != null)
				jsonBuffer.append(tempData);
		} 
		catch (IOException exept) {
			exept.printStackTrace();
		} finally {
			try {
				reader.close(); // stop reading
			} 
			catch (IOException exept) {
				exept.printStackTrace();
			}
		}
		String jsonString = jsonBuffer.toString();
		
		// Parse JSON
		try {
			// Creating JSONObject from String
			JSONObject jsonMainObject = new JSONObject(jsonString);

			// Creating JSONArray from JSONObject
			JSONArray jsonArray = jsonMainObject.getJSONArray("cities");

			// JSONArray has four JSONObject
			for (int i = 0; i < jsonArray.length(); i++) {
	
				// Creating JSONObject from JSONArray
				JSONObject jsonSubObject = jsonArray.getJSONObject(i);
				
				// Get Child Element
				JSONObject currently = jsonSubObject.getJSONObject("currently");
				
				// Find and Set City
				String currentCity = null;
				float lat = jsonSubObject.getInt("latitude");
				float longit = jsonSubObject.getInt("longitude");
				if(lat == 40.6643 && longit == -73.9385) {
					currentCity = "New York, NY";
				}
				if(lat == 34.0194 && longit == -118.4108) {
					currentCity = "Los Angeles, CA";		
				}
				if(lat == 41.8376 && longit == -87.6818) {
					currentCity = "Chicago, IL";
				}
				if(lat == 29.7805 && longit == -95.3863) {
					currentCity = "Houston, TX";
				}
				if(lat == 33.5722 && longit == -112.088) {
					currentCity = "Phoenix, AZ";
				}
				// Variables to pass to class
				String currentSummary = currently.getString("summary");
				String currentIcon = currently.getString("cloudy");
				int currentRainChance = currently.getInt("precipProbability");
				float currentTemp = currently.getInt("temperature");
				float currentWind = currently.getInt("windSpeed");
				String tz = jsonSubObject.getString("timezone");
	
				setClass(currentCity, currentSummary, tz, currentIcon, currentRainChance, currentTemp, currentWind);
			}
		} 
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Set each City in the Class
	public void setClass(String myCity, String mySummary, String myTimeZone, 
			String myIcon, int myRainChance, float myTemp, float myWind) {
		Cities newCity = new Cities(myCity, mySummary, myTimeZone, myIcon, myRainChance, myTemp, myWind);
	}
}



