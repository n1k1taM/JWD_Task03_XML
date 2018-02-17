package by.tc.task03.main;

import by.tc.task03.analyzer.XMLAnalyzer;
import by.tc.task03.analyzer.XMLAnalyzerFactory;
import by.tc.task03.bean.Node;

public class Main {
	public static void main(String[] args) {
		XMLAnalyzerFactory analyzerFactory = XMLAnalyzerFactory.getInstance();
		XMLAnalyzer analyzer = analyzerFactory
				.getXMLAnalalyzer("D:\\Java\\projects\\JWD_Task03_XML\\JWD_Task03_xml\\resources\\notes.xml");

		Node node = null;

		while ((node = analyzer.getNext()) != null) {
			System.out.println(node);
		}
	}

}
