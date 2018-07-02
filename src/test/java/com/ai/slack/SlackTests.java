package com.ai.slack;

import com.ai.slack.data.SlackResponce;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SlackTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	private static String API_URL = "/api";
	private static Logger LOG = LoggerFactory.getLogger(SlackTests.class);

	@BeforeClass
	public static void init(){
		System.setProperty("SLACK_TOKEN", "slack-testing-token");
	}

	@Test
	public void healthcheck() {
		LOG.info("Verify service health check");
		ResponseEntity<String> response = this.restTemplate.getForEntity("/", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Service works!");
	}

	@Test
	public void verifyIncorrectToken() {
		LOG.info("Verify incorrect token");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("token", "INCORRECT-SLACK-TOKEN");
		map.add("command", "/surprise");
		map.add("text", "");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
	}

	@Test
	public void verifyNoTokenInHeader() {
		LOG.info("Verify no command in header");
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("command", "/surprise");
		map.add("text", "");
		verifyIncorrectHeader(map);
	}

	@Test
	public void verifyNoCommandInHeader() {
		LOG.info("Verify no command in header");
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("token", System.getProperty("SLACK_TOKEN"));
		map.add("text", "");
		verifyIncorrectHeader(map);
	}

	@Test
	public void verifyDogService() throws IOException {
		verifyService("Verify Dog service", "dog");
	}

	@Test
	public void verifyWeatherService() throws IOException {
		verifyService("Verify Weather service", "weather");
	}

	@Test
	public void verifyJobService() throws IOException {
		verifyService("Verify Job service", "job");
	}

	@Test
	public void verifyAllServices() throws IOException {
		verifyService("Verify All services", "");
	}

	@Test
	public void verifyIncorrectService() throws IOException {
		LOG.info("Verify incorrect service");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("token", System.getProperty("SLACK_TOKEN"));
		map.add("command", "/surprise");
		map.add("text", "bla-bla-bla");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("Surprise service is running");
	}

	private void verifyIncorrectHeader(MultiValueMap<String, String> map) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	private void verifyService(String title, String commandParameter) throws IOException {
		LOG.info(title);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("token", System.getProperty("SLACK_TOKEN"));
		map.add("command", "/surprise");
		map.add("text", commandParameter);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotEmpty();
		SlackResponce slackResponce = mapper.readValue(response.getBody(), SlackResponce.class);
		assertThat(slackResponce.getText()).isNotEmpty();
		assertThat(slackResponce.getResponseType()).isEqualTo("in_channel");
		assertThat(slackResponce.getAttachments().get(0).getFooter()).isEqualTo("Generated by Surprise service");
		assertThat(slackResponce.getAttachments().get(0).getColor()).isEqualTo("#36a64f");
	}
}
