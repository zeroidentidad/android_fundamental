package com.example.googlemapsexample.datamodel;

import lombok.Data;

@Data
public class Tweet {

	private String _nombre;
	private String _contenido;
	private double _latitude;
	private double _longitude;

}