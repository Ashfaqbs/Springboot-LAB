import java.util.regex.Pattern;

public class Sanitization {



  // cross-site scripting (XSS) risks
 // Sanitizing HTML input is indeed primarily done to mitigate cross-site scripting (XSS) risks

// // Remove forward slashes
// String sanitizedusersPin = StringEscapeUtils.escapeHtml4(usersPin.replace("/", ""));
// String sanitizeduserName = StringEscapeUtils.escapeHtml4(userName.replace("/", ""));

// // Remove backward slashes
// String sanitizedusersPin = StringEscapeUtils.escapeHtml4(usersPin.replace("\\", ""));
// String sanitizeduserName = StringEscapeUtils.escapeHtml4(userName.replace("\\", ""));


//   // Escape forward slashes
// String sanitizedusersPin = StringEscapeUtils.escapeHtml4(usersPin.replaceAll("/", "&#47;"));
// String sanitizeduserName = StringEscapeUtils.escapeHtml4(userName.replaceAll("/", "&#47;"));

// // Escape backward slashes
// String sanitizedusersPin = StringEscapeUtils.escapeHtml4(usersPin.replaceAll("\\\\", "&#92;"));
// String sanitizeduserName = StringEscapeUtils.escapeHtml4(userName.replaceAll("\\\\", "&#92;"));

// Note : org.apache.commons.text.StringEscapeUtils
  
@WebServlet("/submitForm")
public class FormSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        String usersPin = request.getParameter("usersPin");
        String userName = request.getParameter("userName");

      
// Validate userNameParam against regex pattern for report name
if (userNameParam != null && userNameParam.matches("[\\w\\s_/]+")) {
    // Valid report name format
    // Process the report name in your code
} else {
    // Handle invalid input (e.g., display an error message or provide a default value)
}


      String usersPin = request.getParameter("usersPin");

// Validate usersPin against regex pattern for a 4-digit year
if (usersPin != null && usersPin.matches("\\d{4}")) {
    int year = Integer.parseInt(usersPin);
} else {
    // Handle invalid input (e.g., display an error message or provide a default value)
}

// post sanitize      
        // Sanitize input using HTML encoding
        String sanitizedusersPin = StringEscapeUtils.escapeHtml4(usersPin);
        String sanitizeduserName = StringEscapeUtils.escapeHtml4(userName);

        // Process sanitized input (you can perform further validation here)
        // For demonstration purposes, we'll simply print the sanitized values
        System.out.println("Sanitized Reporting Year: " + sanitizedusersPin);
        System.out.println("Sanitized Report Name: " + sanitizeduserName);

        // Redirect or forward the request as needed
        // For demonstration, we'll just send back a success message
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Form Submitted Successfully!</h2>");
        response.getWriter().println("</body></html>");
    }



  
  

// Path/directory traversal vulnerabilities
  // Path/directory traversal vulnerabilities occur when untrusted input is used to construct file paths in file operations,
  // allowing an attacker to access files or directories outside the intended scope. To prevent path traversal vulnerabilities, 
  // you need to sanitize input differently.
    public static String sanitizeFileName(String fileName) {
        // Replace or remove potentially harmful characters
        fileName = fileName.replaceAll("\\.\\.", "")
                           .replaceAll("//", "/")
                           .replaceAll("\\\\", "/");

        // Ensure the file path does not navigate to parent directories
        fileName = fileName.replaceAll("/+", "/"); // Remove consecutive slashes
        fileName = fileName.replaceAll("^\\.*/", ""); // Remove leading slashes and dots
        fileName = fileName.replaceAll("/\\.\\./", "/"); // Remove parent directory navigation

        return fileName;
    }

    // public static void main(String[] args) {
    //     // Test file names
    //     String[] fileNames = {
    //         "example..txt",
    //         "example//file.txt",
    //         "example\\file.txt"
    //     };

    //     // Sanitize and print each file name
    //     for (String fileName : fileNames) {
    //         String sanitizedFileName = sanitizeFileName(fileName);
    //         System.out.println("Original: " + fileName);
    //         System.out.println("Sanitized: " + sanitizedFileName);
    //         System.out.println();
    //     }
    // }
}




  import java.util.regex.Pattern;

public class InputSanitizer {


  //parameter tampering 
    public static boolean isValidInput(String input) {
        // Regular expression to match only alphanumeric characters
        String regex = "^[a-zA-Z0-9]*$";
        
        // Compile the regex
        Pattern pattern = Pattern.compile(regex);
        
        // Return true if the input matches the pattern, false otherwise
        return pattern.matcher(input).matches();
    }

    public static String sanitizeInput(String input) {
        if (!isValidInput(input)) {
            throw new IllegalArgumentException("Invalid input. Only alphanumeric characters are allowed.");
        }
        return input; // Sanitize input if needed, but in this case, we just re-validate it
    }
}



  
