String command = "curl -X GET https://postman-echo.com/get?foo1=bar1&foo2=bar2";
ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
processBuilder.directory(new File("/home/"));
Process process = processBuilder.start();

InputStream inputStream = process.getInputStream();
int exitCode = process.exitValue();

processBuilder.command(new String[]{"curl", "-X", "GET", "https://postman-echo.com?foo=bar"});
process.destroy();



---
Process process = Runtime.getRuntime().exec(command);
BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
String line;
while ((line = reader.readLine()) != null) {
    System.out.println(line);
}
reader.close();
process.destroy();
