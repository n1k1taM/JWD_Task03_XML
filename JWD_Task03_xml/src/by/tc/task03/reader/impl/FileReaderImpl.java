package by.tc.task03.reader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import by.tc.task03.reader.XMLFileReader;

public class FileReaderImpl implements XMLFileReader {
	private long readedCharactersCounter = 0;
	private String filePath;
	public final static int CHAR_PER_READING = 20;

	public FileReaderImpl(String filePath) {
		super();
		this.filePath = filePath;
	}

	@Override
	public String read() throws IOException {
		StringBuilder buffer = new StringBuilder(0);
		try (InputStream in = new FileInputStream(new File(filePath));
				Reader reader = new InputStreamReader(in, "UTF-8");
				Reader bufferReader = new BufferedReader(reader)) {
			int readedChar;
			int cycleReadingCounter = 0;
			bufferReader.skip(readedCharactersCounter);
			while ((readedChar = bufferReader.read()) != -1) {
				char ch = (char) readedChar;
				cycleReadingCounter++;
				if ((cycleReadingCounter > CHAR_PER_READING) && ch == '<') {
					break;
				}
				readedCharactersCounter++;
				buffer.append(ch);
			}
		}
		String string = buffer.toString();
		return string;
	}

	@Override
	public boolean isXMLEpmty() throws IOException {
		try (InputStream in = new FileInputStream(new File(filePath));
				Reader reader = new InputStreamReader(in, "UTF-8");
				Reader bufferReader = new BufferedReader(reader)) {

			bufferReader.skip(readedCharactersCounter);
			if (bufferReader.read() == -1) {
				return true;
			}
		}
		return false;
	}
}
