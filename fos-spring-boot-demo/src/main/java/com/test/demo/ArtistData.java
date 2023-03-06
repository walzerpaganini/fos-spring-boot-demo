package com.test.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Possiamo usare le nostre classi per modellare gli oggetti scambiati da backend e frontend.
 * Di default, Spring usa la libreria Jackson per serializzare gli oggetti Java e deserializzare gli oggetti JSON.
 * 
 * Per serializzare, bisogna scrivere dei getter non privati: Jackson li userà per sapere quali valori scrivere nei campi degli oggetti JSON.
 * Per deserializzare, al contrario, servono dei setter non privati: verranno usati per scrivere negli oggetti Java i valori dei campi degli oggetti JSON.
 * 
 * Di default, Jackson usa i nomi dei getter/setter per mappare automaticamente i campi JSON (es. getFirstName()/setFirstName() -> firstName)
 */

public class ArtistData {
	private Integer id;
	private String firstName;
	private String lastName;
	
	public ArtistData(Integer id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Possiamo usare le annotazioni di Jackson per modificare il modo in cui Spring serializza e deserializza gli oggetti.
	 * Per esempio, con @JsonProperty indichiamo che il campo mappato da questo getter dovrà chiamarsi "identifier" nell'oggetto JSON, e non "id".
	 */
	
	@JsonProperty("identifier")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Non è necessario che un getter restituisca il valore di un singolo campo dell'oggetto Java: possiamo restituire valori a nostro piacimento.
	 * In questo caso la serializzazione in JSON produrrà anche un campo "fullName" contenente la concatenazione di nome e cognome.
	 */
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
}
