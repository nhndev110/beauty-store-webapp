package com.nhndev110.beautystore.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IOUtils {

	public static String toString(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} catch (IOException ex) {
			Logger.getLogger(IOUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				Logger.getLogger(IOUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return sb.toString();
	}

}
