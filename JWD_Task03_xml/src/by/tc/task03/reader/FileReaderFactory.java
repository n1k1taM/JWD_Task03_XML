package by.tc.task03.reader;

import by.tc.task03.reader.impl.FileReaderImpl;

public class FileReaderFactory {
	private final static FileReaderFactory instance = new FileReaderFactory();

	public static FileReaderFactory getInstance() {
		return instance;
	}

	public XMLFileReader getXMLFileReader(String filePath) {

		return new FileReaderImpl(filePath);
	}

}
