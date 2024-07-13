package com.test.ashfaq.util;

import java.util.regex.Pattern;

 class InputSanitizer {


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



  
