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
		assertEquals(1, parser.getPersistenceUnits().size());
	}

	@Test
	public void testJpaVersion() {
		assertEquals("2.1", parser.getJpaVersion());
		assertEquals("2.1", parser.getDefaultPersistenceUnit().getPersistenceXMLSchemaVersion());
	}

	@Test
	public void testPUName() {
		assertEquals("MyPU", parser.getDefaultPersistenceUnit().getPersistenceUnitName());
	}

	@Test
	public void testProviderClass() {
		assertEquals("eisiges.jpa.FakeJPAProvider", parser.getDefaultPersistenceUnit().getPersistenceProviderClassName());
	}

	@Test
	public void testManagedClasses() {
		String fakeEntity = "eisiges.jpa_test.FakeEntity";
		assertEquals(1, parser.getDefaultPersistenceUnit().getManagedClassNames().size());
		assertEquals(fakeEntity, parser.getDefaultPersistenceUnit().getManagedClassNames().get(0));
	}

	@Test
	public void testJtaDataSourceUrl() {
		assertEquals("java:/url-here", parser.getDefaultPersistenceUnit().getJtaDataSourceUrl());
	}

	@Test
	public void testProperties() {
		PersistenceUnitInfoImpl pu = parser.getDefaultPersistenceUnit();
		assertEquals("none", pu.getProperties().getProperty("javax.persistence.schema-generation.database.action"));
		assertEquals("threeve", pu.getProperties().getProperty("fake.jpa.property"));
	}
}
