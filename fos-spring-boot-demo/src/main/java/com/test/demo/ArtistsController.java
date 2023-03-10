package com.test.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("artists")
public class ArtistsController {
	/**
	 * L'annotazione @RequestParam permette di estrapolare parametri dalla query string della richiesta.
	 * Se non indichiamo un nome esplicito, Spring mapperà i parametri basandosi sui nomi dei parametri del metodo.
	 * Questa annotazione rende i parametri obbligatori di default: se vogliamo che siano opzionali, dobbiamo indicarlo esplicitamente.
	 *
	 * In questo caso, una chiamata GET su "/artists?name=Stevie" farà sì che il valore "Stevie" venga assegnato alla stringa "name".
	 * Ricordate che si possono usare anche Collection, Map, e MultiValueMap, per estrapolare più valori o più parametri arbitrari.
	 * 
	 * Le annotazioni @RequestHeader e @CookieValue si usano in modo simile, ma leggono (ovviamente) dagli header e dai cookie della richiesta.
	 */
	
	@GetMapping
	private String getAllArtists(@RequestParam(name="first-name", required=false) String name) {		
		return "Stai cercando artisti che si chiamano " + name;
	}
	
	/** 
	 * L'annotazione @RequestBody indica a Spring che deve usare il body della richiesta per costruire un oggetto Java di un certo tipo.
	 * In particolare, Spring cercherà di deserializzare l'oggetto JSON inviato dal client e passerà al metodo l'oggetto Java creato.
	 * (Vedi ArtistData.java)
	 */
	
	@PostMapping
	private String addNewArtist(@RequestBody ArtistData artistData) {
		return artistData.getLastName();
	}
	
	/**
	 * Usando l'annotazione @PathVariable, possiamo estrapolare il valore di un parametro dal path.
	 * In questo caso, l'endpoint risponde a chiamate GET sui path "/artists/1", "/artists/2", e così via.
	 *
	 * Per questo metodo utilizziamo come tipo di ritorno la classe generica ResponseEntity: notate che
	 * possiamo usarla per restituire diversi tipi di body in caso di successo o insuccesso della richiesta,
	 * e anche per impostare esplicitamente il codice di stato HTTP da inviare al client.
	 * 
	 * Notate che in caso di successo restituiamo un oggetto di una nostra classe: Spring lo serializzerà in
	 * formato JSON per costruire il body della risposta. Questo meccanismo funziona anche restituendo direttamente
	 * l'oggetto in questione, senza incapsularlo in un oggetto di classe ResponseEntity.
	 * (Vedi ArtistData.java)
	 */
	
	@GetMapping("{id}")
	private ResponseEntity<?> getArtistDetails(@PathVariable("id") int artistId) throws ArtistNotFoundException {
		if(artistId == 0) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Artista con ID " + artistId + " non trovato.");
		}
		
		ArtistData ad = new ArtistData(artistId, "Stevie", "Wonder");
		return ResponseEntity.ok(ad);
	}
	
	/**
	 * Possiamo anche estrapolare più parametri da un singolo path, distinguendoli con nomi diversi.
	 * Al solito, se non indichiamo un nome esplicito, Spring cercherà di mappare i valori usando i 
	 * nomi dei parametri del metodo.
	 */
	
	@GetMapping("{artistId}/albums/{albumId}")
	private String getDetailOfAlbum(@PathVariable Integer artistId, @PathVariable Long albumId) throws ArtistNotFoundException {
		if(artistId == 0) {
			// Sollevando questa eccezione, la gestione della richiesta passerà a un metodo annotato
			// con @ExceptionHandler (se il controller ne contiene uno adatto).
			
			throw new ArtistNotFoundException(artistId);
		}
		
		return "Dettagli dell'album " + albumId + " dell'artista con ID " + artistId;
	}

	/**
	 * Annotando un metodo con @ExceptionHandler, esso viene invocato ogni volta che un altro metodo del
	 * controller solleva una delle eccezioni indicate. Si può usare @ResponseStatus per stabilire il codice 
	 * di stato HTTP, e allo stesso tempo restituire un oggetto a nostro piacimento come body della risposta.
	 */
	
	@ExceptionHandler({ ArtistNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private String handleArtistNotFound(ArtistNotFoundException e) {
		return "Artista con ID " + e.getArtistId() + " non trovato.";
	}
}
