package by.tc.task03.reader;

import java.io.IOException;

public interface XMLFileReader {
	String read() throws IOException, IOException;

	boolean isXMLEpmty() throws IOException;

}
