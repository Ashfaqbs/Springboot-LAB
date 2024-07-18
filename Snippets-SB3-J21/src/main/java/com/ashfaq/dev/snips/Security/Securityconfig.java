import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { // no required to implements WebMvcConfigurer for below but try it our 

// CORS Config 
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                  registry.addMapping("/**")  // Apply CORS settings to all paths
                        .allowedOrigins("http://my-frontend-domain.com")  // Replace with frontend domain, provide domain in list way "URL1 ,URL2" and avoid *
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // HTTP methods allowed
                        .allowedHeaders("*")  // Allow all headers
                        // .allowCredentials(true)  // Allow credentials like cookies and authorization headers if we provide this we cant provide multiple origin
                          .maxAge(3600);  // Cache preflight response for 1 hour (3600 seconds)
                         // maxAge(3600): Sets the maximum age (in seconds) for which the results of a preflight request can be cached by the client.  it's set to 1 hour (3600 seconds).
                //its for HTTP option
            }
        };
    }
}
