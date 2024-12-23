## Curl Process Caller 


```
String command = "curl -X GET https://postman-echo.com/get?foo1=bar1&foo2=bar2";
ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
processBuilder.directory(new File("/home/"));
Process process = processBuilder.start();

InputStream inputStream = process.getInputStream();
int exitCode = process.exitValue();

processBuilder.command(new String[]{"curl", "-X", "GET", "https://postman-echo.com?foo=bar"});
process.destroy();
```


``````
Process process = Runtime.getRuntime().exec(command);
BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
String line;
while ((line = reader.readLine()) != null) {
    System.out.println(line);
}
reader.close();
process.destroy();
``````



```
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class YourClass {

    // ...

    public List<DataObject> processListInParallel(List<DataObject> dataList) {
        // Create an ExecutorService with a fixed number of threads
        int numThreads = Runtime.getRuntime().availableProcessors(); // Use the number of available processors
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        try {
            return dataList.parallelStream()
                    .map(d -> executorService.submit(() -> {
                        String response = curlC(d.getLink());
                        d.setResponse(response);
                        return d;
                    }))
                    .map(future -> {
                        try {
                            return future.get(); // Get the result from the Future
                        } catch (Exception e) {
                            e.printStackTrace(); // Handle exception if needed
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
        } finally {
            // Shutdown the executor service
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle interruption if needed
            }
        }
    }

    // ...

    private String curlC(String link) {
        // Your existing implementation of curlC
        // Ensure this function is optimized for performance
        // ...
        return "response";  // Replace with the actual response
    }

    // ...
}
```