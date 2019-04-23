package eisiges.persistence_xml_parser;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public final class PersistenceUnitInfoImpl implements PersistenceUnitInfo {

	private final String persistenceUnitName;
	private final String persistenceProviderClassName;
	private final PersistenceUnitTransactionType transactionType;
	private final DataSource jtaDataSource;
	private final String jtaDataSourceUrl;
	private final DataSource nonJtaDataSource;
	private final String nonJtaDataSourceUrl;
	private final List<String> mappingFileNames;
	private final URL persistenceUnitRootUrl;
	private final List<String> managedClassNames;
	private final boolean excludeUnlistedClasses;
	private final Properties properties;
	private final String persistenceXMLSchemaVersion;

	@Override
	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}

	@Override
	public String getPersistenceProviderClassName() {
		return persistenceProviderClassName;
	}

	@Override
	public PersistenceUnitTransactionType getTransactionType() {
		if (transactionType == null) {
			return PersistenceUnitTransactionType.RESOURCE_LOCAL;
		}
		return transactionType;
	}

	@Override
	public DataSource getJtaDataSource() {
		return jtaDataSource;
	}

	public String getJtaDataSourceUrl() {
		return jtaDataSourceUrl;
	}

	@Override
	public DataSource getNonJtaDataSource() {
		return nonJtaDataSource;
	}

	public String getNonJtaDataSourceUrl() {
		return nonJtaDataSourceUrl;
	}

	@Override
	public List<String> getMappingFileNames() {
		return mappingFileNames;
	}

	@Override
	public List<URL> getJarFileUrls() {
		return Collections.<URL>emptyList();
	}

	@Override
	public URL getPersistenceUnitRootUrl() {
		return persistenceUnitRootUrl;
	}

	@Override
	public List<String> getManagedClassNames() {
		return managedClassNames;
	}

	@Override
	public boolean excludeUnlistedClasses() {
		return excludeUnlistedClasses;
	}

	@Override
	public SharedCacheMode getSharedCacheMode() {
		return SharedCacheMode.UNSPECIFIED; // good default?
	}

	@Override
	public ValidationMode getValidationMode() {
		return ValidationMode.AUTO; // good default?
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public String getPersistenceXMLSchemaVersion() {
		return persistenceXMLSchemaVersion;
	}

	@Override
	public ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	@Override
	public void addTransformer(ClassTransformer transformer) {
	}

	@Override
	public ClassLoader getNewTempClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}
