package com.test.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  Se usiamo l'annotazione @Controller, il valore di ritorno di ciascun metodo sarà interpretato
 *  da Spring come il nome di una vista (es. un file HTML). Spring supporta anche il templating 
 *  tramite Thymeleaf per restituire pagine generate dinamicamente.
 */

@Controller 
@RequestMapping("api-mvc")
public class DemoMvcController {
	
	@GetMapping("view")
	private String getHelloView() {
		return "/hello.html"; // Il server risponde con il contenuto del file "/hello.html"
	}
	
	/**
	 *	Annotando un metodo con @ResponseBody, facciamo sì che Spring serializzi il valore di
	 *	ritorno (di default in JSON) e lo restituisca al client come risposta.
	 */
	
	@GetMapping("response-body")
	@ResponseBody
	private String getHelloResponseBody() {
		return "/hello.html"; // Il server risponde con la stringa "/hello.html"
	}
	
}
