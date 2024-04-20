import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Autowired
    private EmailService emailService;

    public void makeApiCallAndSendEmailIfNeeded() {
        String apiUrl = "your_api_endpoint_here";
        ResponseEntity<String> response = new RestTemplate().getForEntity(apiUrl, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            // API call failed, extract relevant info from response
            String errorResponse = response.getBody();
            String errorMessage = "Extracted error message: " + errorResponse;

            // Send email notification
            emailService.sendEmail(errorMessage);
        }
    }
}
