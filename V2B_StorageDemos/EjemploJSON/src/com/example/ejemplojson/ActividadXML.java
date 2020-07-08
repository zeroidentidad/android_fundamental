package com.example.ejemplojson;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;

public class ActividadXML extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		XmlPullParserFactory factory;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(new StringReader ("<div>Hello</div>"));
			
			int eventType  = xpp.getEventType();
			
			while (eventType != XmlPullParser.END_DOCUMENT ) {
				if (eventType == XmlPullParser.START_DOCUMENT) 
					System.out.println("El documento ha comenzado");
				else if (eventType == XmlPullParser.START_TAG) 
					System.out.println("Start tag " + xpp.getName());
				else if (eventType == XmlPullParser.END_TAG) 
					System.out.println("END tag " + xpp.getName());
				else if (eventType == XmlPullParser.TEXT) 
					System.out.println("TEXT " + xpp.getText());
				eventType = xpp.next();
			}
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
