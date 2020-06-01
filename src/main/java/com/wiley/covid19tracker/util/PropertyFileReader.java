package com.wiley.covid19tracker.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PropertyFileReader {

	private static final Logger log = Logger.getLogger(PropertyFileReader.class);
	
	private Properties readPropFile(String propfilePath) {
		log.info("prop file path : {}"+ propfilePath);
		InputStream input = null;
		Properties prop = new Properties();
		try {
			input = this.getClass().getResourceAsStream(propfilePath);
			prop.load(input);
			return prop;
		} catch ( IOException ex) {
			log.error("Error occured while reading property file : {}"+ propfilePath);
			return prop;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Properties getAppInitPropertiesByFile(String propfileName) {
		return readPropFile("/env_based/" + propfileName);
	}
}
