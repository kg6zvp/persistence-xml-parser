package eisiges.persistence_xml_parser;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PersistenceXmlParserTest {

	PersistenceXmlParser parser;

	@Before
	public void parseFile() throws Exception {
		parser = new PersistenceXmlParser();
		parser.parse();
	}

	@Test
	public void testNumberOfPersistenceUnits() {
		assertEquals(1, parser.puList.size());
	}

	@Test
	public void testJpaVersion() {
		assertEquals("2.1", parser.getJpaVersion());
	}

	@Test
	public void testPUName() {
		assertEquals("MyPU", parser.puList.get(0).getPersistenceUnitName());
	}

	@Test
	public void testProviderClass() {
		assertEquals("eisiges.jpa.FakeJPAProvider", parser.puList.get(0).getPersistenceProviderClassName());
	}

	@Test
	public void testProperties() {
		assertEquals("none", parser.puList.get(0).getProperties().getProperty("javax.persistence.schema-generation.database.action"));
		assertEquals("threeve", parser.puList.get(0).getProperties().getProperty("fake.jpa.property"));
	}
}
