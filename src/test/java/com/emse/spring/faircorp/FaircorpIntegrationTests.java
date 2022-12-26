package com.emse.spring.faircorp;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;


import org.springframework.boot.test.context.SpringBootTest;

import java.net.http.HttpResponse;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class FaircorpIntegrationTests {

	@Test
	void ensureThatUserAPICallReturnStatusCode200() throws Exception {
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api-adresse.data.gouv.fr/search/?q=cours+fauriel+&limit=15")).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		assertThat(response.statusCode()).isEqualTo(200);

	}

	@Test
	@DisplayName("Ensures that the content type starts with application/json")
	void ensureThatJsonIsReturnedAsContentType() throws Exception {
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api-adresse.data.gouv.fr/search/?q=cours+fauriel+&limit=15")).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		Optional<String> firstValue = response.headers().firstValue("Content-Type");
		String string = firstValue.get();
		assertThat(string).startsWith("application/json");

	}

	@Test
	@DisplayName ("Ensure that the JSON for the name cours fauriel contains a reference to the actual address")
	void ensureJsonContainsAddress() throws Exception {
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api-adresse.data.gouv.fr/search/?q=cours+fauriel+&limit=15")).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String body = response.body();
		// For easy to see the output
		//System.out.println(body);
		assertTrue(body.contains("label\":\"Cours Fauriel 42100 Saint-Étienne"));



	}

}
