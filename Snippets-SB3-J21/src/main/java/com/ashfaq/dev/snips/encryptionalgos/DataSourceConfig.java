
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Value("${db.username.encrypted}")
    private String encryptedUsername;

    @Value("${db.password.encrypted}")
    private String encryptedPassword;

    @Value("${db.encryption.secret-key}")
    private String secretKey;
//
//    @Bean
//    public javax.sql.DataSource dataSource() throws Exception {
//        AESDecryptor decryptor = new AESDecryptor(secretKey);
//
//        String username = decryptor.decrypt(encryptedUsername);
//        String password = decryptor.decrypt(encryptedPassword);
//
//        // Set up your DataSource with decrypted username and password
//        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setUrl("jdbc:mysql://localhost:3306/yourdb");
//
//        return dataSource;
//    }
}
