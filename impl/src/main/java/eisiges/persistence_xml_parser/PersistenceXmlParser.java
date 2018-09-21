package eisiges.persistence_xml_parser;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PersistenceXmlParser {

	String jpaVersion;

	List<PersistenceUnitInfoImpl> puList = new LinkedList<>();

	public void parse() throws IOException, ParserConfigurationException, SAXException {
		parse(Thread.currentThread().getContextClassLoader());
	}

	public void parse(ClassLoader classLoader) throws IOException, ParserConfigurationException, SAXException {
		parse(classLoader.getResource("META-INF/persistence.xml"));
	}

	public void parse(URL persistenceXmlFile) throws IOException, ParserConfigurationException, SAXException {
		URLConnection conn = persistenceXmlFile.openConnection();
		conn.setUseCaches(false);
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = db.parse(conn.getInputStream());

		Element persistenceElement = doc.getDocumentElement();
		jpaVersion = persistenceElement.getAttribute("version");

		NodeList nList = persistenceElement.getElementsByTagName("persistence-unit");
		for (int i = 0; i < nList.getLength(); ++i) {
			Node n = nList.item(i);
			if (n instanceof Element) {
				parsePersistenceUnit((Element) n);
			}
		}
	}

	public String getJpaVersion() {
		return jpaVersion;
	}

	private void parsePersistenceUnit(Element element) {
		PersistenceUnitInfoImpl info = new PersistenceUnitInfoImpl();
		info.setPersistenceUnitName(element.getAttribute("name"));

		NodeList providerTagList = element.getElementsByTagName("provider");
		if (providerTagList.getLength() > 0) {
			info.setPersistenceProviderClassName(providerTagList.item(0).getTextContent());
		}

		NodeList propertiesTagList = element.getElementsByTagName("properties");
		if (propertiesTagList.getLength() > 0) {
			NodeList nList = ((Element) propertiesTagList.item(0)).getElementsByTagName("property");
			Properties properties = new Properties();
			for (int i = 0; i < nList.getLength(); ++i) {
				Element property = (Element) nList.item(i);
				properties.setProperty(property.getAttribute("name"), property.getAttribute("value"));
			}
			info.setProperties(properties);
		}

		puList.add(info);
	}
}
