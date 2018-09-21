package eisiges.persistence_xml_parser;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.persistence.spi.PersistenceUnitInfo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class PersistenceXmlParser {
	String jpaVersion;
	public void parse(URL persistenceXmlFile) throws IOException, ParserConfigurationException, SAXException {
		URLConnection conn = persistenceXmlFile.openConnection();
		conn.setUseCaches(false);
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = db.parse(conn.getInputStream());
		
		Element persistenceElement = doc.getDocumentElement();
		jpaVersion = persistenceElement.getAttribute("version");
	}

	public String getJpaVersion() {
		return jpaVersion;
	}
}