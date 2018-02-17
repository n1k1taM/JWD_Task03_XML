package by.tc.task03.analyzer;

import by.tc.task03.analyzer.impl.XMLAnalyzerImpl;

public class XMLAnalyzerFactory {

	private final static XMLAnalyzerFactory instance = new XMLAnalyzerFactory();

	private XMLAnalyzerFactory() {
		super();
	}

	public static XMLAnalyzerFactory getInstance() {
		return instance;
	}

	public XMLAnalyzer getXMLAnalalyzer(String filePath) {

		return new XMLAnalyzerImpl(filePath);
	}

}
