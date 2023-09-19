package com.test.ashfaq.controller;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Scope("session")
//@Scope("singleton")
@Controller
public class FormController {

	@GetMapping("/")
	public String default_View(HttpSession session) {
		System.out.println("Calling default view");
		String csrfToken = (String) session.getAttribute("csrfToken");
		if (csrfToken == null) {
			csrfToken = UUID.randomUUID().toString();
			session.setAttribute("csrfToken", csrfToken);
		}
		System.out.println(csrfToken + " is the csrfToken ");

		return "form";
	}

	@ResponseBody
	@GetMapping("/test")
	public String Test_View() {
		FormController main = new FormController();

		System.out.println(main.hashCode() + " is the hascode ");
		return "form";
	}

	// validation
	@GetMapping("/submitForm")
	public String submitForm(HttpServletRequest request, HttpSession session) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		String csrf = request.getParameter("_csrf");
		// Process the form data
		System.out.println("Submitted Name: " + name);
		System.out.println("Submitted Email: " + email);
		System.out.println("Submitted message: " + message);
		System.out.println("Submitted csrf: " + csrf);

		String csrfToken = (String) session.getAttribute("csrfToken");
		if (csrfToken == null) {
		System.out.println("Token in null");
		}

		System.out.println(csrfToken + " is the session token");
		System.out.println(csrf + " is the input tag token");
		// You can perform further logic here, such as storing the data or returning a
		// response

		return "form"; // Return the name of the JSP file to display a result page (result.jsp)

	}

	public static void main(String[] args) {
		FormController main = new FormController();
		System.out.println(main.hashCode() + " is the hascode ");

	}

}
