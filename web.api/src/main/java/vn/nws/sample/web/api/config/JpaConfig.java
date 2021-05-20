package vn.nws.sample.web.api.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("vn.nws.sample.dao")
@EntityScan(basePackages = "vn.nws.sample.dao")
public class JpaConfig extends JpaBaseConfiguration {
	protected JpaConfig(DataSource dataSource, JpaProperties properties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
		super(dataSource, properties, jtaTransactionManager);
	}
	
	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		return new EclipseLinkJpaVendorAdapter();
	}

	@Override
	protected Map<String, Object> getVendorProperties() {
		HashMap<String, Object> map = new HashMap<>();
	    map.put(PersistenceUnitProperties.WEAVING, this.detectWeavingMode());
	    map.put(PersistenceUnitProperties.DDL_GENERATION, "none");
	    map.put(PersistenceUnitProperties.TARGET_DATABASE, "PostgreSQL");
	    map.put(PersistenceUnitProperties.QUERY_CACHE, "false");
	    map.put(PersistenceUnitProperties.CACHE_SHARED_DEFAULT, "false");
	    return map;
	}
	
	private String detectWeavingMode() {
        return InstrumentationLoadTimeWeaver.isInstrumentationAvailable() ? "true" : "static";
    }
}
