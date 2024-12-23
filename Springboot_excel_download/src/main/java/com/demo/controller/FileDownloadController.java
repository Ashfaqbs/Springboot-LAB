
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/download")
public class FileDownloadController {

	@GetMapping("/template")
	public ResponseEntity<InputStreamResource> downloadTemplate() throws IOException {
		// Locate the Excel file in the resources folder
    // file saved in src/main/static/booking.xlsx
		ClassPathResource templateFile = new ClassPathResource("static/booking.xlsx");

		// Set up the response with headers
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=booking.xlsx");

		// Return the file with correct media type and headers
		return ResponseEntity.ok().headers(headers)
				.contentType(
						MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(new InputStreamResource(templateFile.getInputStream()));
	}
}
