

    package com.example.product_web_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Description {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String details;
    private String companySite;
    private String email;
    private String supportLocation;
}



package com.example.product_web_service.entity;
import org.springframework.context.annotation.Description;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String productCategory;
    private String brandName;
    private String physicalStore;
    
    @Lob
    private byte[] image;
    
    @Lob
    private byte[] productVideo;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private Description description;
}




when running the code Error.


      - @ConditionalOnProperty (spring.datasource.jndi-name) did not find property 'jndi-name' (OnPropertyCondition)
      Matched:
         - @ConditionalOnClass found required classes 'javax.sql.DataSource', 'org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType' (OnClassCondition)

   JndiJtaConfiguration:
      Did not match:
         - @ConditionalOnJndi JNDI environment is not available (OnJndiCondition)
      Matched:
         - @ConditionalOnClass found required class 'org.springframework.transaction.jta.JtaTransactionManager' (OnClassCondition)

   JooqAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.jooq.DSLContext' (OnClassCondition)

   JpaRepositoriesAutoConfiguration#entityManagerFactoryBootstrapExecutorCustomizer:
      Did not match:
         - AnyNestedCondition 0 matched 2 did not; NestedCondition on JpaRepositoriesAutoConfiguration.BootstrapExecutorCondition.LazyBootstrapMode @ConditionalOnProperty (spring.data.jpa.repositories.bootstrap-mode=lazy) did not find property 'bootstrap-mode'; NestedCondition on JpaRepositoriesAutoConfiguration.BootstrapExecutorCondition.DeferredBootstrapMode @ConditionalOnProperty (spring.data.jpa.repositories.bootstrap-mode=deferred) did not find property 'bootstrap-mode' (JpaRepositoriesAutoConfiguration.BootstrapExecutorCondition)

   JsonbAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'jakarta.json.bind.Jsonb' (OnClassCondition)

   JsonbHttpMessageConvertersConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'jakarta.json.bind.Jsonb' (OnClassCondition)

   KafkaAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.kafka.core.KafkaTemplate' (OnClassCondition)

   LdapAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.ldap.core.ContextSource' (OnClassCondition)

   LdapRepositoriesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.data.ldap.repository.LdapRepository' (OnClassCondition)    

   LiquibaseAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'liquibase.change.DatabaseChange' (OnClassCondition)

   LocalDevToolsAutoConfiguration:
      Did not match:
         - Initialized Restarter Condition initialized without URLs (OnInitializedRestarterCondition)

   MailSenderAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'jakarta.mail.internet.MimeMessage' (OnClassCondition)

   MailSenderValidatorAutoConfiguration:
      Did not match:
         - @ConditionalOnSingleCandidate did not find required type 'org.springframework.mail.javamail.JavaMailSenderImpl' (OnBeanCondition)

   MessageSourceAutoConfiguration:
      Did not match:
         - ResourceBundle did not find bundle with basename messages (MessageSourceAutoConfiguration.ResourceBundleCondition)

   MongoAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.mongodb.client.MongoClient' (OnClassCondition)

   MongoDataAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.mongodb.client.MongoClient' (OnClassCondition)

   MongoReactiveAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.mongodb.reactivestreams.client.MongoClient' (OnClassCondition)

   MongoReactiveDataAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.mongodb.reactivestreams.client.MongoClient' (OnClassCondition)

   MongoReactiveRepositoriesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.mongodb.reactivestreams.client.MongoClient' (OnClassCondition)

   MongoRepositoriesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.mongodb.client.MongoClient' (OnClassCondition)

   MustacheAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.samskivert.mustache.Mustache' (OnClassCondition)

   Neo4jAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.neo4j.driver.Driver' (OnClassCondition)

   Neo4jDataAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.neo4j.driver.Driver' (OnClassCondition)

   Neo4jReactiveDataAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.neo4j.driver.Driver' (OnClassCondition)

   Neo4jReactiveRepositoriesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.neo4j.driver.Driver' (OnClassCondition)

   Neo4jRepositoriesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.neo4j.driver.Driver' (OnClassCondition)

   NettyAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'io.netty.util.NettyRuntime' (OnClassCondition)

   OAuth2AuthorizationServerAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.oauth2.server.authorization.OAuth2Authorization' (OnClassCondition)

   OAuth2AuthorizationServerJwtAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.oauth2.server.authorization.OAuth2Authorization' (OnClassCondition)

   OAuth2ClientAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.config.annotation.web.configuration.EnableWebSecurity' (OnClassCondition)

   OAuth2ResourceServerAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken' (OnClassCondition)

   ProjectInfoAutoConfiguration#buildProperties:
      Did not match:
         - @ConditionalOnResource did not find resource '${spring.info.build.location:classpath:META-INF/build-info.properties}' (OnResourceCondition)

   ProjectInfoAutoConfiguration#gitProperties:
      Did not match:
         - GitResource did not find git info at classpath:git.properties (ProjectInfoAutoConfiguration.GitResourceAvailableCondition)      

   PulsarAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.apache.pulsar.client.api.PulsarClient' (OnClassCondition)

   PulsarReactiveAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.apache.pulsar.client.api.PulsarClient' (OnClassCondition)

   QuartzAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.quartz.Scheduler' (OnClassCondition)

   R2dbcAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'io.r2dbc.spi.ConnectionFactory' (OnClassCondition)

   R2dbcDataAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.data.r2dbc.core.R2dbcEntityTemplate' (OnClassCondition)    

   R2dbcInitializationConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required classes 'io.r2dbc.spi.ConnectionFactory', 'org.springframework.r2dbc.connection.init.DatabasePopulator' (OnClassCondition)

   R2dbcProxyAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'io.r2dbc.proxy.ProxyConnectionFactory' (OnClassCondition)

   R2dbcRepositoriesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'io.r2dbc.spi.ConnectionFactory' (OnClassCondition)

   R2dbcTransactionManagerAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.r2dbc.connection.R2dbcTransactionManager' (OnClassCondition)

   RSocketGraphQlClientAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'graphql.GraphQL' (OnClassCondition)

   RSocketMessagingAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'io.rsocket.RSocket' (OnClassCondition)

   RSocketRequesterAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'io.rsocket.RSocket' (OnClassCondition)

   RSocketSecurityAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.rsocket.core.SecuritySocketAcceptorInterceptor' (OnClassCondition)

   RSocketServerAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'io.rsocket.core.RSocketServer' (OnClassCondition)

   RSocketStrategiesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'io.netty.buffer.PooledByteBufAllocator' (OnClassCondition)

   RabbitAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.rabbitmq.client.Channel' (OnClassCondition)

   ReactiveElasticsearchClientAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'co.elastic.clients.transport.ElasticsearchTransport' (OnClassCondition)        

   ReactiveElasticsearchRepositoriesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'reactor.core.publisher.Mono' (OnClassCondition)

   ReactiveMultipartAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.web.reactive.config.WebFluxConfigurer' (OnClassCondition)  

   ReactiveOAuth2ClientAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'reactor.core.publisher.Flux' (OnClassCondition)

   ReactiveOAuth2ResourceServerAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity' (OnClassCondition)

   ReactiveSecurityAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'reactor.core.publisher.Flux' (OnClassCondition)

   ReactiveUserDetailsServiceAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.authentication.ReactiveAuthenticationManager' (OnClassCondition)

   ReactiveWebServerFactoryAutoConfiguration:
      Did not match:
         - @ConditionalOnWebApplication did not find reactive web application classes (OnWebApplicationCondition)

   ReactorAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'reactor.core.publisher.Hooks' (OnClassCondition)

   RedisAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.data.redis.core.RedisOperations' (OnClassCondition)        

   RedisCacheConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.data.redis.connection.RedisConnectionFactory' (OnClassCondition)

   RedisReactiveAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'reactor.core.publisher.Flux' (OnClassCondition)

   RedisRepositoriesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.data.redis.repository.configuration.EnableRedisRepositories' (OnClassCondition)

   RemoteDevToolsAutoConfiguration:
      Did not match:
         - @ConditionalOnProperty (spring.devtools.remote.secret) did not find property 'secret' (OnPropertyCondition)
      Matched:
         - @ConditionalOnClass found required classes 'jakarta.servlet.Filter', 'org.springframework.http.server.ServerHttpRequest' (OnClassCondition)

   RepositoryRestMvcAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration' (OnClassCondition)

   Saml2RelyingPartyAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository' (OnClassCondition)

   SecurityAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.authentication.DefaultAuthenticationEventPublisher' (OnClassCondition)

   SecurityFilterAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.config.http.SessionCreationPolicy' (OnClassCondition)

   SendGridAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'com.sendgrid.SendGrid' (OnClassCondition)

   ServletWebServerFactoryAutoConfiguration.ForwardedHeaderFilterConfiguration:
      Did not match:
         - @ConditionalOnProperty (server.forward-headers-strategy=framework) did not find property 'server.forward-headers-strategy' (OnPropertyCondition)

   ServletWebServerFactoryConfiguration.EmbeddedJetty:
      Did not match:
         - @ConditionalOnClass did not find required classes 'org.eclipse.jetty.server.Server', 'org.eclipse.jetty.util.Loader', 'org.eclipse.jetty.ee10.webapp.WebAppContext' (OnClassCondition)

   ServletWebServerFactoryConfiguration.EmbeddedUndertow:
      Did not match:
         - @ConditionalOnClass did not find required classes 'io.undertow.Undertow', 'org.xnio.SslClientAuthMode' (OnClassCondition)       

   SessionAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.session.Session' (OnClassCondition)

   SpringApplicationAdminJmxAutoConfiguration:
      Did not match:
         - @ConditionalOnProperty (spring.application.admin.enabled=true) did not find property 'enabled' (OnPropertyCondition)

   TaskExecutorConfigurations.SimpleAsyncTaskExecutorBuilderConfiguration#simpleAsyncTaskExecutorBuilderVirtualThreads:
      Did not match:
         - @ConditionalOnMissingBean (types: org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder; SearchStrategy: all) found beans of type 'org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder' simpleAsyncTaskExecutorBuilder (OnBeanCondition)

   TaskExecutorConfigurations.TaskExecutorConfiguration#applicationTaskExecutorVirtualThreads:
      Did not match:
         - @ConditionalOnThreading did not find VIRTUAL (OnThreadingCondition)

   TaskSchedulingAutoConfiguration#scheduledBeanLazyInitializationExcludeFilter:
      Did not match:
         - @ConditionalOnBean (names: org.springframework.context.annotation.internalScheduledAnnotationProcessor; SearchStrategy: all) did not find any beans named org.springframework.context.annotation.internalScheduledAnnotationProcessor (OnBeanCondition)

   TaskSchedulingConfigurations.SimpleAsyncTaskSchedulerBuilderConfiguration#simpleAsyncTaskSchedulerBuilderVirtualThreads:
      Did not match:
         - @ConditionalOnMissingBean (types: org.springframework.boot.task.SimpleAsyncTaskSchedulerBuilder; SearchStrategy: all) found beans of type 'org.springframework.boot.task.SimpleAsyncTaskSchedulerBuilder' simpleAsyncTaskSchedulerBuilder (OnBeanCondition)

   TaskSchedulingConfigurations.TaskSchedulerConfiguration:
      Did not match:
         - @ConditionalOnBean (names: org.springframework.context.annotation.internalScheduledAnnotationProcessor; SearchStrategy: all) did not find any beans named org.springframework.context.annotation.internalScheduledAnnotationProcessor (OnBeanCondition)

   ThymeleafAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.thymeleaf.spring6.SpringTemplateEngine' (OnClassCondition)

   TransactionAutoConfiguration#transactionalOperator:
      Did not match:
         - @ConditionalOnSingleCandidate (types: org.springframework.transaction.ReactiveTransactionManager; SearchStrategy: all) did not find any beans (OnBeanCondition)

   TransactionAutoConfiguration.AspectJTransactionManagementConfiguration:
      Did not match:
         - @ConditionalOnBean (types: org.springframework.transaction.aspectj.AbstractTransactionAspect; SearchStrategy: all) did not find any beans of type org.springframework.transaction.aspectj.AbstractTransactionAspect (OnBeanCondition)

   TransactionAutoConfiguration.EnableTransactionManagementConfiguration.JdkDynamicAutoProxyConfiguration:
      Did not match:
         - @ConditionalOnProperty (spring.aop.proxy-target-class=false) did not find property 'proxy-target-class' (OnPropertyCondition)   

   UserDetailsServiceAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.security.authentication.AuthenticationManager' (OnClassCondition)

   ValidationAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'jakarta.validation.executable.ExecutableValidator' (OnClassCondition)

   WebClientAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.web.reactive.function.client.WebClient' (OnClassCondition) 

   WebFluxAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.web.reactive.config.WebFluxConfigurer' (OnClassCondition)  

   WebMvcAutoConfiguration#hiddenHttpMethodFilter:
      Did not match:
         - @ConditionalOnProperty (spring.mvc.hiddenmethod.filter.enabled) did not find property 'enabled' (OnPropertyCondition)

   WebMvcAutoConfiguration.ProblemDetailsErrorHandlingConfiguration:
      Did not match:
         - @ConditionalOnProperty (spring.mvc.problemdetails.enabled=true) did not find property 'enabled' (OnPropertyCondition)

   WebMvcAutoConfiguration.ResourceChainCustomizerConfiguration:
      Did not match:
         - @ConditionalOnEnabledResourceChain did not find class org.webjars.WebJarVersionLocator (OnEnabledResourceChainCondition)        

   WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#beanNameViewResolver:
      Did not match:
         - @ConditionalOnMissingBean (types: org.springframework.web.servlet.view.BeanNameViewResolver; SearchStrategy: all) found beans of type 'org.springframework.web.servlet.view.BeanNameViewResolver' beanNameViewResolver (OnBeanCondition)

   WebServiceTemplateAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.oxm.Marshaller' (OnClassCondition)

   WebServicesAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.ws.transport.http.MessageDispatcherServlet' (OnClassCondition)

   WebSessionIdResolverAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'reactor.core.publisher.Mono' (OnClassCondition)

   WebSocketMessagingAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer' (OnClassCondition)

   WebSocketReactiveAutoConfiguration:
      Did not match:
         - @ConditionalOnWebApplication did not find reactive web application classes (OnWebApplicationCondition)

   WebSocketServletAutoConfiguration.JettyWebSocketConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'org.eclipse.jetty.ee10.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer' (OnClassCondition)

   WebSocketServletAutoConfiguration.UndertowWebSocketConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'io.undertow.websockets.jsr.Bootstrap' (OnClassCondition)

   XADataSourceAutoConfiguration:
      Did not match:
         - @ConditionalOnBean (types: org.springframework.boot.jdbc.XADataSourceWrapper; SearchStrategy: all) did not find any beans of type org.springframework.boot.jdbc.XADataSourceWrapper (OnBeanCondition)
      Matched:
         - @ConditionalOnClass found required classes 'javax.sql.DataSource', 'jakarta.transaction.TransactionManager', 'org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType' (OnClassCondition)


Exclusions:
-----------

    None


Unconditional classes:
----------------------

    org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration

    org.springframework.boot.autoconfigure.ssl.SslAutoConfiguration

    org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration

    org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration

    org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration

    org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration



2025-01-30T12:22:59.571+05:30  INFO 20800 --- [product-web-service] [           main] .s.b.a.l.ConditionEvaluationReportLogger : 

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2025-01-30T12:22:59.611+05:30 ERROR 20800 --- [product-web-service] [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Association 'com.example.product_web_service.entity.Product.description' targets the type 'org.springframework.context.annotation.Description' which is not an '@Entity' type
      
      
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Association 'com.example.product_web_service.entity.Product.description' targets the type 'org.springframework.context.annotation.Description' which is not an '@Entity' type
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1812) ~[spring-beans-6.2.2.jar:6.2.2]
        a

[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 6.705 s <<< FAILURE! -- in com.example.product_web_service.ProductWebServiceApplicationTests
[ERROR] com.example.product_web_service.ProductWebServiceApplicationTests.contextLoads -- Time elapsed: 0.033 s <<< ERROR!
java.lang.IllegalStateException: Failed to load ApplicationContext for [WebMergedContextConfiguration@59371066 testClass = com.example.product_web_service.ProductWebServiceApplicationTests, locations = [], classes = [com.example.product_web_service.ProductWebServiceApplication], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = ["org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true"], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@41330d4f, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@508dec2b, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@2f67a4d3, org.springframework.boot.test.web.reactor.netty.DisableReactorResourceFactoryGlobalResourcesContextCustomizerFactory$DisableReactorResourceFactoryGlobalResourcesContextCustomizerCustomizer@3b6d844d, org.springframework.boot.test.autoconfigure.OnFailureConditionReportContextCustomizerFactory$OnFailureConditionReportContextCustomizer@730d2164, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@78fa769e, org.springframework.test.context.support.DynamicPropertiesContextCustomizer@0, org.springframework.boot.test.context.SpringBootTestAnnotation@c26310c1], resourceBasePath = "src/main/webapp", contextLoader = org.springframework.boot.test.context.SpringBootContextLoader, parent = null]
        at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:180)
      
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Association 'com.example.product_web_service.entity.Product.description' targets the type 'org.springframework.context.annotation.Description' which is not an '@Entity' type
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1812)
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:601)
      
        ... 19 more
Caused by: org.hibernate.AnnotationException: Association 'com.example.product_web_service.entity.Product.description' targets the type 'org.springframework.context.annotation.Description' which is not an '@Entity' type
        at org.hibernate.boot.model.internal.ToOneFkSecondPass.doSecondPass(ToOneFkSecondPass.java:110)
      




Fix:
### **Issue Explanation**
The error indicates that Hibernate is trying to map the `description` field in the `Product` entity to `org.springframework.context.annotation.Description`, which is not a JPA entity. This happens because there is a conflict between your `Description` entity and Spring’s `@Description` annotation.

### **Root Cause**
- You have an entity class named `Description`, which is also a commonly used Spring annotation (`org.springframework.context.annotation.Description`).
- Hibernate mistakenly refers to the Spring annotation instead of your `Description` entity.

### **Solution**
Rename your `Description` entity to avoid the naming conflict.

#### **Steps to Fix**
1. **Rename `Description` entity**  
   Change `Description` to `ProductDescription` or another meaningful name.

2. **Update all references**  
   - Modify the entity class name:
     ```java
     @Entity
     @Getter @Setter @NoArgsConstructor @AllArgsConstructor
     class ProductDescription {  // Changed from Description
         @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;
         private String details;
         private String companySite;
         private String email;
         private String supportLocation;
     }
     ```
   - Update the `Product` entity reference:
     ```java
     @OneToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "description_id")
     private ProductDescription description;  // Updated reference
     ```
   - Adjust repository and service references accordingly.

3. **Restart the application**  
   - Ensure the changes are applied by cleaning and rebuilding the project.

### **Expected Outcome**
Renaming the entity should resolve the conflict, allowing Hibernate to correctly recognize `ProductDescription` as an entity instead of referring to the Spring annotation.

---


### Error 2

http://localhost:8080/products/category/Tablet


2025-01-31T07:29:20.911+05:30 DEBUG 18576 --- [product-web-service] [nio-8080-exec-7] o.s.web.servlet.DispatcherServlet        : GET "/products/category/Tablet", parameters={}
2025-01-31T07:29:20.911+05:30 DEBUG 18576 --- [product-web-service] [nio-8080-exec-7] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to com.example.product_web_service.controller.ProductController#findByProductCategory(String)
2025-01-31T07:29:20.926+05:30 DEBUG 18576 --- [product-web-service] [nio-8080-exec-7] o.s.web.servlet.DispatcherServlet        : Failed to complete request: org.springframework.orm.jpa.JpaSystemException: Unable to access lob stream
2025-01-31T07:29:20.926+05:30 ERROR 18576 --- [product-web-service] [nio-8080-exec-7] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.orm.jpa.JpaSystemException: Unable to access lob stream] with root cause

org.postgresql.util.PSQLException: Large Objects may not be used in auto-commit mode.
        at org.postgresql.largeobject.LargeObjectManager.open(LargeObjectManager.java:244) ~[postgresql-42.7.5.jar:42.7.5]
        at org.postgresql.largeobject.LargeObjectManager.open(LargeObjectManager.java:230) ~[postgresql-42.7.5.jar:42.7.5]
        at org.postgresql.jdbc.AbstractBlobClob.getLo(AbstractBlobClob.java:288) ~[postgresql-42.7.5.jar:42.7.5]
        at org.postgresql.jdbc.AbstractBlobClob.getBinaryStream(AbstractBlobClob.java:127) ~[postgresql-42.7.5.jar:42.7.5]
        at org.hibernate.type.descriptor.java.PrimitiveByteArrayJavaType.wrap(PrimitiveByteArrayJavaType.java:133) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.type.descriptor.java.PrimitiveByteArrayJavaType.wrap(PrimitiveByteArrayJavaType.java:29) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.type.descriptor.jdbc.BlobJdbcType$1.doExtract(BlobJdbcType.java:62) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final] 
        at org.hibernate.type.descriptor.jdbc.BasicExtractor.extract(BasicExtractor.java:44) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final] 
        at org.hibernate.sql.results.jdbc.internal.JdbcValuesResultSetImpl.getCurrentRowValue(JdbcValuesResultSetImpl.java:387) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.internal.RowProcessingStateStandardImpl.getJdbcValue(RowProcessingStateStandardImpl.java:152) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.graph.basic.BasicResultAssembler.extractRawValue(BasicResultAssembler.java:54) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.graph.basic.BasicResultAssembler.assemble(BasicResultAssembler.java:60) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.graph.entity.internal.EntityInitializerImpl.extractConcreteTypeStateValues(EntityInitializerImpl.java:1603) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.graph.entity.internal.EntityInitializerImpl.initializeEntityInstance(EntityInitializerImpl.java:1329) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.graph.entity.internal.EntityInitializerImpl.initializeInstance(EntityInitializerImpl.java:1308) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.graph.entity.internal.EntityInitializerImpl.initializeInstance(EntityInitializerImpl.java:97) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.internal.StandardRowReader.coordinateInitializers(StandardRowReader.java:244) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.internal.StandardRowReader.readRow(StandardRowReader.java:141) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.spi.ListResultsConsumer.read(ListResultsConsumer.java:249) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.spi.ListResultsConsumer.consume(ListResultsConsumer.java:201) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.results.spi.ListResultsConsumer.consume(ListResultsConsumer.java:35) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.exec.internal.JdbcSelectExecutorStandardImpl.doExecuteQuery(JdbcSelectExecutorStandardImpl.java:224) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.exec.internal.JdbcSelectExecutorStandardImpl.executeQuery(JdbcSelectExecutorStandardImpl.java:102) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.exec.spi.JdbcSelectExecutor.executeQuery(JdbcSelectExecutor.java:91) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.sql.exec.spi.JdbcSelectExecutor.list(JdbcSelectExecutor.java:165) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]   
        at org.hibernate.query.sqm.internal.ConcreteSqmSelectQueryPlan.lambda$new$1(ConcreteSqmSelectQueryPlan.java:152) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.query.sqm.internal.ConcreteSqmSelectQueryPlan.withCacheableSqmInterpretation(ConcreteSqmSelectQueryPlan.java:442) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.query.sqm.internal.ConcreteSqmSelectQueryPlan.performList(ConcreteSqmSelectQueryPlan.java:362) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.query.sqm.internal.QuerySqmImpl.doList(QuerySqmImpl.java:380) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]       
        at org.hibernate.query.spi.AbstractSelectionQuery.list(AbstractSelectionQuery.java:143) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.hibernate.query.Query.getResultList(Query.java:120) ~[hibernate-core-6.6.5.Final.jar:6.6.5.Final]
        at org.springframework.data.jpa.repository.query.JpaQueryExecution$CollectionExecution.doExecute(JpaQueryExecution.java:130) ~[spring-data-jpa-3.4.2.jar:3.4.2]
        at org.springframework.data.jpa.repository.query.JpaQueryExecution.execute(JpaQueryExecution.java:93) ~[spring-data-jpa-3.4.2.jar:3.4.2]
        at org.springframework.data.jpa.repository.query.AbstractJpaQuery.doExecute(AbstractJpaQuery.java:152) ~[spring-data-jpa-3.4.2.jar:3.4.2]
        at org.springframework.data.jpa.repository.query.AbstractJpaQuery.execute(AbstractJpaQuery.java:140) ~[spring-data-jpa-3.4.2.jar:3.4.2]
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:170) ~[spring-data-commons-3.4.2.jar:3.4.2]
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:158) ~[spring-data-commons-3.4.2.jar:3.4.2]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:170) ~[spring-data-commons-3.4.2.jar:3.4.2]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:149) ~[spring-data-commons-3.4.2.jar:3.4.2]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.2.2.jar:6.2.2]
        at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:69) ~[spring-data-commons-3.4.2.jar:3.4.2]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.2.2.jar:6.2.2]
        at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:380) ~[spring-tx-6.2.2.jar:6.2.2]
        at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119) ~[spring-tx-6.2.2.jar:6.2.2]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.2.2.jar:6.2.2]
        at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:138) ~[spring-tx-6.2.2.jar:6.2.2]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.2.2.jar:6.2.2]
        at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:136) ~[spring-data-jpa-3.4.2.jar:3.4.2]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.2.2.jar:6.2.2]
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:223) ~[spring-aop-6.2.2.jar:6.2.2]
        at jdk.proxy12/jdk.proxy12.$Proxy158.findByProductCategory(Unknown Source) ~[na:na]
        at com.example.product_web_service.service.ProductService.findByProductCategory(ProductService.java:26) ~[classes/:na]
        at com.example.product_web_service.controller.ProductController.findByProductCategory(ProductController.java:79) ~[classes/:na]    
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
        at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:257) ~[spring-web-6.2.2.jar:6.2.2]
        at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:190) ~[spring-web-6.2.2.jar:6.2.2]
        at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118) ~[spring-webmvc-6.2.2.jar:6.2.2]
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:986) ~[spring-webmvc-6.2.2.jar:6.2.2]
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:891) ~[spring-webmvc-6.2.2.jar:6.2.2]
        at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-6.2.2.jar:6.2.2]
        at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1088) ~[spring-webmvc-6.2.2.jar:6.2.2]      
        at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:978) ~[spring-webmvc-6.2.2.jar:6.2.2]        
        at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014) ~[spring-webmvc-6.2.2.jar:6.2.2]    
        at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903) ~[spring-webmvc-6.2.2.jar:6.2.2]
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564) ~[tomcat-embed-core-10.1.34.jar:6.0]
        at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885) ~[spring-webmvc-6.2.2.jar:6.2.2]
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658) ~[tomcat-embed-core-10.1.34.jar:6.0]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51) ~[tomcat-embed-websocket-10.1.34.jar:10.1.34]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-6.2.2.jar:6.2.2]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.2.jar:6.2.2]       
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-6.2.2.jar:6.2.2]      
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.2.jar:6.2.2]       
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-6.2.2.jar:6.2.2]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.2.jar:6.2.2]       
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167) ~[tomcat-embed-core-10.1.34.jar:10.1.34]    
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90) ~[tomcat-embed-core-10.1.34.jar:10.1.34]     
        at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483) ~[tomcat-embed-core-10.1.34.jar:10.1.34] 
        at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) ~[tomcat-embed-core-10.1.34.jar:10.1.34]       
        at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:397) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) ~[tomcat-embed-core-10.1.34.jar:10.1.34]       
        at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:905) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741) ~[tomcat-embed-core-10.1.34.jar:10.1.34]    
        at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) ~[tomcat-embed-core-10.1.34.jar:10.1.34]        
        at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[tomcat-embed-core-10.1.34.jar:10.1.34]
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63) ~[tomcat-embed-core-10.1.34.jar:10.1.34]     
        at java.base/java.lang.Thread.run(Thread.java:1583) ~[na:na]

2025-01-31T07:29:20.931+05:30 DEBUG 18576 --- [product-web-service] [nio-8080-exec-7] o.s.web.servlet.DispatcherServlet        : "ERROR" dispatch for GET "/error", parameters={}
2025-01-31T07:29:20.931+05:30 DEBUG 18576 --- [product-web-service] [nio-8080-exec-7] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController#error(HttpServletRequest)
2025-01-31T07:29:20.932+05:30 DEBUG 18576 --- [product-web-service] [nio-8080-exec-7] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Using 'application/json', given [*/*] and supported [application/json, application/*+json]
2025-01-31T07:29:20.933+05:30 DEBUG 18576 --- [product-web-service] [nio-8080-exec-7] o.s.w.s.m.m.a.HttpEntityMethodProcessor  : Writing [{timestamp=Fri Jan 31 07:29:20 IST 2025, status=500, error=Internal Server Error, trace=org.springfr (truncated)...]
2025-01-31T07:29:20.934+05:30 DEBUG 18576 --- [product-web-service] [nio-8080-exec-7] o.s.web.servlet.DispatcherServlet        : Exiting from "ERROR" dispatch, status 500
2025-01-31T07:54:28.638+05:30  WARN 18576 --- [product-web-service] [l-9 housekeeper] com.zaxxer.hikari.pool.HikariPool        : HikariPool-9 - Thread starvation or clock leap detected (housekeeper delta=2m54s612ms235┬╡s900ns).

Resolution add @Transactional on Service function :

````
  @Transactional
    public List<Product> findByProductCategory(String productCategory) {
        return productRepository.findByProductCategory(productCategory);
    }
````


Explanation:

### Issue: **"Large Objects may not be used in auto-commit mode."**
The error occurs because PostgreSQL does not allow Large Object (LOB) retrieval in **auto-commit mode**. Hibernate is trying to fetch a **BLOB (Binary Large Object)** or **CLOB (Character Large Object)**, and PostgreSQL requires a transaction for such operations.

---

### **Possible Causes**
1. **Auto-commit Mode Enabled**:  
   - PostgreSQL JDBC driver does not allow LOB operations in **auto-commit mode**.
   
2. **LOB (BLOB/CLOB) Column in Query**:  
   - If your `Product` entity contains a **byte[]** or `@Lob` field, Hibernate is trying to fetch it.
   
3. **Spring Transaction Not Applied Properly**:  
   - If your service method does not have `@Transactional`, Hibernate might be executing in auto-commit mode.

---

### **Solutions**
#### ✅ **Solution 1: Disable Auto-commit in DataSource (Recommended)**
Manually configure the **DataSource** to disable auto-commit mode.

- **For HikariCP (Spring Boot default pool):**
  ```properties
  spring.datasource.hikari.auto-commit=false
  ```
  OR in Java configuration:
  ```java
  @Bean
  public DataSource dataSource(DataSourceProperties properties) {
      HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
      dataSource.setAutoCommit(false);  // Disable auto-commit
      return dataSource;
  }
  ```
---

#### ✅ **Solution 2: Use `@Transactional` on Service Method**
Ensure that your repository call runs inside a transaction.
```java
@Service
public class ProductService {
    
    @Transactional  // Ensures that LOBs are fetched in a transaction
    public List<Product> findByProductCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
```
---

#### ✅ **Solution 3: Use `@Basic(fetch = FetchType.LAZY)` for LOB Fields**
If your `Product` entity has a `@Lob` field, set **lazy loading** so it loads only when required.
```java
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)  // Prevents eager loading of LOB
    private byte[] image;  // or a CLOB field

    // Getters & Setters
}
```
---

### **Next Steps**
1. **Check if the `Product` entity contains a `@Lob` field (BLOB/CLOB).**
2. **Try adding `@Transactional` to the service method.**
3. **Ensure `spring.datasource.hikari.auto-commit=false` is set.**
4. **Check database connection settings to make sure auto-commit is off.**
