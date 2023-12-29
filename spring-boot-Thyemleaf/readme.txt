    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc8</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
    spring.datasource.username=username
    spring.datasource.password=password
    ```
    @Repository
    public class MyRepository {

        @Autowired
        private JdbcTemplate jdbcTemplate;
    }
    ```
    public List<Map<String, Object>> runQuery() {
        String sql = "your SQL query here";
        return jdbcTemplate.queryForList(sql);
    }
    ```
    @Autowired
    private MyRepository myRepository;

    public void printQueryResults() {
        List<Map<String, Object>> results = myRepository.runQuery();
        // print or process the results
    }
    ```
