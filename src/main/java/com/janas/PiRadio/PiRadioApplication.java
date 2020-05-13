package com.janas.PiRadio;

import com.janas.PiRadio.RadioStations.RadioStations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiRadioApplication {

	public static void main(String[] args) {
		for(int i = 0; i < args.length; i++) {
			if (args[i].equals("--custom-stations-path"))
				RadioStations.givenPath = args[i+1];
		}
		SpringApplication.run(PiRadioApplication.class, args);
	}
}
