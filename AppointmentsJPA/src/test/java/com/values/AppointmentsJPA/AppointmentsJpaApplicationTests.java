package com.values.AppointmentsJPA;

import com.values.AppointmentsJPA.controller.PrenotazioneController;
import com.values.AppointmentsJPA.model.Prenotazione;
import com.values.AppointmentsJPA.model.PrenotazioneView;
import com.values.AppointmentsJPA.model.Sede;
import com.values.AppointmentsJPA.service.PrenotazioneService;
import com.values.AppointmentsJPA.service.SedeService;
import com.values.AppointmentsJPA.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AppointmentsJpaApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testPrenotazioni_shouldReturnPrenotazioneView() throws Exception {

		// Send GET request to "/prenotazione"
		ResponseEntity<String> response = restTemplate.getForEntity("/prenotazione", String.class);

		// Assert status code (assuming successful access)
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void testPrenotazioni1_shouldCreatePrenotazioneAndRedirect() throws Exception {

		// Prepare a sample Prenotazione object (replace with your actual data)
		Prenotazione newPrenotazione = new Prenotazione(1L, LocalDate.now(), LocalTime.now());

		// Set headers for JSON request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Prenotazione> requestEntity = new HttpEntity<>(newPrenotazione, headers);

		// Send POST request to "/prenotazione"
		ResponseEntity<String> response = restTemplate.postForEntity("/prenotazione", requestEntity, String.class);

		// Assert status code (assuming successful creation)
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		// You can assert the redirect location if applicable using response.getHeaders().getLocation()
	}

	// Modify a prenotazione (assuming prenId exists)
	@Test
	public void testModify_shouldUpdatePrenotazione() throws Exception {
		long prenId = 1L; // Replace with actual ID
		LocalDate newDate = LocalDate.now().plusDays(1);
		LocalTime newTime = LocalTime.of(11, 0);

		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("pren_id", String.valueOf(prenId));
		params.add("date", newDate.toString());
		params.add("time", newTime.toString());

		restTemplate.postForEntity("/prenotazione/modifica", params, String.class);

		// Assertions specific to your update logic (optional)
	}

	// Delete a prenotazione (assuming prenId exists)
	@Test
	public void testDeletePren_shouldDeletePrenotazione() throws Exception {
		long prenId = 1L; // Replace with actual ID

		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("pren_id", String.valueOf(prenId));

		restTemplate.postForEntity("/prenotazione/delete", params, String.class);

		// Assertions specific to your deletion logic (optional)
	}


	/*
	@Mock
	private SedeService sedeService;
	@Mock
	private PrenotazioneService prenotazioneService;
	@Mock
	private UserService userService;
	@Autowired
	private Model model;
	@Autowired
	private RedirectAttributes redirectAttributes;

	@Test
	public void testPrenotazioni_shouldReturnPrenotazioneView() throws Exception {
		//Mock services and session attributes
		String username = "a@a.com";
		long userId = 3;
		List<PrenotazioneView> prenotazioni = new ArrayList<>();
		List<Sede> sedi = new ArrayList<>();
		HttpSession sessionMock = Mockito.mock(HttpSession.class);
		Mockito.when(sessionMock.getAttribute("username")).thenReturn(username);
		Mockito.when(userService.selectUserId(username)).thenReturn(Long.valueOf(username));
		Mockito.when(prenotazioneService.findAll(userId)).thenReturn(prenotazioni);
		Mockito.when(sedeService.findAll()).thenReturn(sedi);

		//Call the controller method
		PrenotazioneController controller = new PrenotazioneController(sedeService, prenotazioneService, userService);
		String viewName = controller.prenotazioni(model, sessionMock);

		// Verify interactions with mocked services
		Mockito.verify(sessionMock).getAttribute("username");
		Mockito.verify(userService).selectUserId(username);
		Mockito.verify(prenotazioneService).findAll(userId);
		Mockito.verify(sedeService).findAll();

		// Assert view name and model attributes
		assertThat(viewName).isEqualTo("prenotazione");
		assertThat(model.asMap().get("prenotazioni")).isEqualTo(prenotazioni);
		assertThat(model.asMap().get("sedi")).isEqualTo(sedi);
	}

	@Test
	void contextLoads() {
	}
	 */

}
