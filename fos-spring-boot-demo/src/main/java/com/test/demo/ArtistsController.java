package com.test.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping // 
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
	 * Notate che restituiamo un oggetto di una nostra classe: Spring lo serializzerà in formato JSON per costruire il body della risposta.
	 * (Vedi ArtistData.java)
	 */
	
	@GetMapping("{id}")
	private ArtistData getArtistDetails(@PathVariable("id") int artistId) {
		return new ArtistData(artistId, "Stevie", "Wonder");
	}
	
	/**
	 * Possiamo anche estrapolare più parametri da un singolo path, distinguendoli con nomi diversi.
	 * Al solito, se non indichiamo un nome esplicito, Spring cercherà di mappare i valori usando i nomi dei parametri del metodo.
	 */
	
	@GetMapping("{artistId}/albums/{albumId}")
	private String getDetailOfAlbum(@PathVariable Long artistId, @PathVariable Long albumId) {
		return "Dettagli dell'album " + albumId + " dell'artista con ID " + artistId;
	}

}
