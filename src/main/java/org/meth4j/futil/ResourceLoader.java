package org.meth4j.futil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.common.base.Charsets;

public class ResourceLoader {

	public static Properties loadProperties(String name) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = ResourceLoader.class.getResourceAsStream(name);
			if (in != null) {
				props.load(in);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
		return props;
	}

	public static Properties loadPropertiesXML(String name) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = ResourceLoader.class.getResourceAsStream(name);
			if (in != null) {
				props.loadFromXML(in);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return props;
	}

	public static String loadString(String name) {
		InputStream in = null;
		try {
			in = ResourceLoader.class.getResourceAsStream(name);
			try {
				return new java.util.Scanner(in, Charsets.UTF_8.name()).useDelimiter("\\A").next();
			} catch (java.util.NoSuchElementException e) {
				return "";
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
