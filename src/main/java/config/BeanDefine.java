package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class BeanDefine {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

//	@Bean
//	public PasswordEncoder PasswordEncoder() {	
//	}
//	
//	@Bean
//	Mapper mapper() {
//		return new Mapper();
//	}
//}
//	@Configuration
//	public class RepositoryConfig {
//	    @Bean
//	    public DataSource dataSource() {
//	        return ...;
//	    }
