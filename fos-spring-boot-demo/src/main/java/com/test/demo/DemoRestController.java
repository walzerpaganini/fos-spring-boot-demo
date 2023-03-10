package com.test.demo;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *  L'annotazione @RestController fa sì che questa classe venga usata da Spring per intercettare
 *  e gestire richieste HTTP. In particolare, in questo caso la classe intercetterà tutte le chiamate
 *  che arriveranno al path "/api-rest".
 *  
 *  Usando @RestController, viene implicitamente applicata l'annotazione @ResponseBody ai metodi.
 *  (Vedi DemoMvcController.java)
 *  
 *  Le annotazioni @GetMapping, @PostMapping, ecc. determinano i verbi HTTP a cui i metodi risponderanno.
 */

@RestController
@RequestMapping("api-rest")
public class DemoRestController {
	
	@GetMapping // Risponde a chiamate GET su "/api-rest"
	private String handleGetRequests() {
		return "Received GET on /api-rest";
	}
	
	@PostMapping // Risponde a chiamate POST su "/api-rest"
	private String handlePostRequests() {
		return "Received POST on /api-rest";
	}
	
	/**
	 * Si possono anche aggiungere altri frammenti al path del controller.
	 */
	
	@GetMapping("sub") // Risponde a chiamate GET su "/api-rest/sub"
	private String handleMoreSpecificGetRequests() {
		return "Received GET on /api-rest/sub";
	}
	
	/**
	 * Se aggiungiamo un parametro di tipo HttpServletRequest o HttpServletResponse,
	 * Spring che inietterà un oggetto corrispondente che rappresenta rispettivamente
	 * la richiesta HTTP ricevuta e la risposta HTTP che verrà inviata al client.
	 * Usando i metodi delle loro interfacce, possiamo conoscere qualsiasi dettaglio
	 * tecnico della richiesta e modificare qualsiasi dettaglio tecnico della risposta.
	 * 
	 * Notate che in questo caso, volendo, potremmo perfino lasciare il metodo con tipo
	 * di ritorno void e scrivere direttamente i byte nello stream della risposta.
	 */
	
	@GetMapping("servlet-stuff")
	private void httpRequestAndResponse(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println(req.getCharacterEncoding());
		
		res.setStatus(409);
		res.addCookie(new Cookie("mycookie", "value"));
		
		res.getOutputStream().write("Prova".getBytes());
	}
}
