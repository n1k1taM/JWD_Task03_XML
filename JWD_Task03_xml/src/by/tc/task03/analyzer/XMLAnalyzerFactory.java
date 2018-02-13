package by.tc.task03.analyzer;

import by.tc.task03.analyzer.impl.XMLAnalyzerImpl;

public class XMLAnalyzerFactory {
	
	private static XMLAnalyzer instance = null;
	
	private XMLAnalyzerFactory(){
		super();
	}
	
	public static XMLAnalyzer getInstance(){
		if (instance == null) {
			instance = new XMLAnalyzerImpl();
		}
		return instance;
	}
	
}
