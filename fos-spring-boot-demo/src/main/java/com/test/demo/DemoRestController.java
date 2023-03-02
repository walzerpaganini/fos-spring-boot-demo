package com.test.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
