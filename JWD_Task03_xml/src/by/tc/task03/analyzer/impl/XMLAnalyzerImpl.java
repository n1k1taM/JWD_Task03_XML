package by.tc.task03.analyzer.impl;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.tc.task03.analyzer.XMLAnalyzer;
import by.tc.task03.bean.Node;
import by.tc.task03.bean.NodeType;
import by.tc.task03.reader.FileReaderFactory;
import by.tc.task03.reader.XMLFileReader;

public class XMLAnalyzerImpl implements XMLAnalyzer {
	private final XMLFileReader fileReader;
	private StringBuilder buffer = new StringBuilder();

	public XMLAnalyzerImpl(String filePath) {
		super();
		FileReaderFactory readerFactory = FileReaderFactory.getInstance();
		fileReader = readerFactory.getXMLFileReader(filePath);
	}

	@Override
	public Node getNext() {
		try {
			if ((buffer.toString().trim().length() == 0)) {// слишком длинный вызов, хоть и общеихвестных методов
				if (fileReader.isXMLEpmty()) {// fileReader - не говорит, что ты читаешь их xml, а метод уже привязывает
					// вот такая нестыковка в именовании может вывести на ошибки в коде - наличие этого метода не очевидно, так как непонятно, почему это сам ридер может быть пустым
					return null;
				}

				buffer.append(fileReader.read());
			}
		} catch (IOException e) {

			e.printStackTrace();// А ЭТО ЧТО ТАКОЕ? что это за гашение исключеноия?
		}
		trimBuffer(buffer);// действительно обрезаем буфер?
		NodeType nodeType = getNodeType(buffer);
		Node node = getNode(buffer, nodeType);
		deleteNodeFromBuffer(node);

		return node;
	}

	private NodeType getNodeType(StringBuilder buffer) {

		if (buffer.charAt(0) != '<') {// ну когда же ты уже начнешь именовать константы в коде?
			return NodeType.CHARACTERS;
		} else if (buffer.charAt(1) == '/') {
			return NodeType.CLOSE_TAG;
		}
		int index = buffer.indexOf(">");
		if (buffer.charAt(index - 1) == '/') {// в этом методе некрасивая какая-то логика
			return NodeType.EMPTY_TAG;
		}
		return NodeType.OPEN_TAG;

	}

	private Node getNode(StringBuilder buffer, NodeType nodeType) {
		Node node = null;
		if (nodeType == NodeType.CHARACTERS) {
			node = getCaractersNode(buffer, nodeType);
		} else {
			node = getTagNode(buffer, nodeType);
		}// а если типов узлов будет 20? что делать будем?

		return node;
	}

	private Node getCaractersNode(StringBuilder buffer, NodeType type) {
		Node node = new Node();
		node.setType(type);
		int index = buffer.indexOf("<");
		if (index == -1) {
			node.setContent(buffer.toString());
		} else {
			node.setContent(buffer.substring(0, index));
		}
		return node;
	}

	private Node getTagNode(StringBuilder buffer, NodeType type) {
		Node node = new Node();
		node.setType(type);
		int index = buffer.indexOf(">");
		node.setContent(buffer.substring(0, index + 1));
		return node;
	}

	private void deleteNodeFromBuffer(Node node) {
		buffer.delete(0, node.getContent().length());
	}

	void trimBuffer(StringBuilder buffer) {
		Pattern p = Pattern.compile("^[\\s]+");
		final Matcher matcher = p.matcher(buffer.toString());
		if (matcher.find()) {
			int index = matcher.end();
			char firstNonWhiteChar = buffer.charAt(index);
			if (firstNonWhiteChar == '<') {
				buffer.delete(0, index);
			}
		}

	}
}
