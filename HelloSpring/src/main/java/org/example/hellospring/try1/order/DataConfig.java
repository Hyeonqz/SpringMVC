package org.example.hellospring.try1.order;

import javax.sql.DataSource;

import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class DataConfig {
	// h2 datasource
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	// entityMangerFactory 를 가지고 entityManger 를 만든다.
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("org.example.hellospring");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {
			{
			setDatabase(Database.H2);
			setGenerateDdl(true);
			setShowSql(true);
		}
		});

		return emf;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public BeanPostProcessor persistenceUnitPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

}
