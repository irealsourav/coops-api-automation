package com.takeaway.coopsAPIAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class FileManager {

	private Properties props;
	private String result = "";
	private File file;
	private InputStream input;

	public FileManager(String fileName) throws FileNotFoundException {
		props = new Properties();
		result = "";
		file = new File(fileName);
		input = new FileInputStream(file);
	}

	public String getPropertiesData(String inputkey) throws IOException {

		if (input != null) {
			props.load(input);
			result = props.getProperty(inputkey);
		}
		return result;
	}

	public boolean setPropertiesData(String key, String NewValue) throws IOException {

		if (input != null) {
			props.load(input);
			props.setProperty(key, NewValue);
			OutputStream out = new FileOutputStream(file);
			props.store(out, "save");
			//input.close();
			return true;
		}

		return false;
	}

}