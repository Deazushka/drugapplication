package by.training.drugspayapplication.config.db;


import by.training.drugspayapplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;

@Configuration
@PropertySource({ "classpath:application.properties" })
public class DBConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource commonDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource templateDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.jdbcUrl"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;

    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("templateDataSource") DataSource templateDataSource) {
        return new NamedParameterJdbcTemplate(templateDataSource);
    }

    @Bean
    public StateRepository stateRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new StateRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public ProductRepository productRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new ProductRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public PatientRepository patientRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new PatientRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public TransactionRepository transactionRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new TransactionRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public AuditOperationRepository auditOperationRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new AuditOperationRepository(namedParameterJdbcTemplate);
    }

//    @Bean
//    public PostProxyInvokerAuditOperationListener postProxyInvokerAuditOperationListener() {
//        return postProxyInvokerAuditOperationListener();
//    }
}
