package com.example.ejemplojson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private JSONObject jsonObject;
	private TextView textView;
	
	private String stringParsedValue = null;
	private String strJSONValue =  "{\"FirstObject\":{\"attr1\":\"one value\" ,\"attr2\":\"two value\","
			+"\"sub\": { \"sub1\":[ {\"sub1_attr\":\"sub1_attr_value\" },{\"sub1_attr\":\"sub2_attr_value\" }]}}}";


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textView);
		parseJSON();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void parseJSON() {
		try {
			jsonObject = new JSONObject(strJSONValue);
			JSONObject object = jsonObject.getJSONObject("FirstObject");
			String attr1 = object.getString("attr1");
			String attr2 = object.getString("attr2");
			
			stringParsedValue = "Valor de attr1 = " + attr1;
			stringParsedValue += "\nValor de attr2 = " + attr2;
			
			JSONObject subObject = object.getJSONObject("sub");
			JSONArray subArray = subObject.getJSONArray("sub1");
			
			stringParsedValue += "\nLongitud del array es" + subArray.length();
			textView.setText(stringParsedValue);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/*
	{
	    "FirstObject": {
	        "attr1": "one value",
	        "attr2": "two value",
	        "sub": {
	            "sub1": [
	                {
	                    "sub1_attr": "sub1_attr_value"
	                },
	                {
	                    "sub1_attr": "sub2_attr_value"
	                }
	            ]
	        }
	    }
	} */

}
