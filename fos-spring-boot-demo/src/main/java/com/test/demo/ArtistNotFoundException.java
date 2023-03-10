package com.test.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Uno dei possibili modi per restituire codici di stato diversi da 200, è quello di annotare le nostre eccezioni
 * con @ResponseStatus. Tuttavia, di default Spring restituirà al browser una pagina che contiene lo stacktrace
 * dell'errore, mentre altri client (es. Postman) si vedranno restituire un JSON informativo creato automaticamente
 * da Spring: in altre parole, questo metodo non è ideale se vogliamo mandare al cliente un body di nostra scelta.
 */

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Artista non trovato")
public class ArtistNotFoundException extends Exception {
	private int artistId;
	
	public ArtistNotFoundException(int artistId) {
		this.artistId = artistId;
	}
	
	public int getArtistId() {
		return artistId;
	}
}
