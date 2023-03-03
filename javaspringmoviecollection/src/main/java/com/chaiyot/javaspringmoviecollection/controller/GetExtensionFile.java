package com.chaiyot.javaspringmoviecollection.controller;

import java.io.File;

public class GetExtensionFile {

	static String GetEx(String filename) {
		String extension = "";
		int index = filename.lastIndexOf('.');
		if (index > 0) {
			extension = filename.substring(index);
			System.out.println(filename + "\t" + extension);
		}

		return extension;
	}
}
