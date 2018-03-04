package program.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DateTimeException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import program.exception.InvalidPlateNumberException;

/**
 * Testing plates with 6 or 7 digits with formats XXX000, XXX0000, XXX-000 and XXX-0000 and with spaces between the numbers and letters
 * @author german
 * 
 */
public class PicoAndPlacaServiceTest {
	
	private String date = "01/01/2018";
	
	private String time = "8:00";
	
	private String plateNumber = "ABC001";

	private PicoAndPlacaService picoAndPlacaService;
	
	@Rule
    public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() {
		picoAndPlacaService = new PicoAndPlacaService();
	}
	
	/*
	 * Plates that alert message of pico and placa
	 */
	@Test
	public void shouldAlert() throws DateTimeException, InvalidPlateNumberException {
		// Test cases
		date = "01/01/2018"; // Monday
		time = "18:00";
		plateNumber = "ABC001";
		assertTrue(picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time));
		
		date = "01 / 01/2018"; // Monday
		time = "7:09";
		plateNumber = "WAQ - 0501";
		assertTrue(picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time));
		
		date = "05-01-2018"; // Friday
		time = "17 : 09";
		plateNumber = "XYA2340";
		assertTrue(picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time));
		
		date = "30/01 /2018"; // Tuesday
		time = "16: 31";
		plateNumber = "XQA-2343";
		assertTrue(picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time));
	}
	
	/*
	 * Plates that doesn't give any alert because they aren't in a forbidden date
	 */
	@Test
	public void shouldPass() throws DateTimeException, InvalidPlateNumberException {
		// Test cases
		date = "01/01/2018"; // Monday
		time = "6:00";
		plateNumber = "ACB9001";
		assertFalse(picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time));
		
		date = "02/01/2018"; // Tuesday
		time = "8:0 2";
		plateNumber = "AXC001";
		assertFalse(picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time));
		
		date = "04 -01 -2018"; // Thursday
		time = "16 : 09";
		plateNumber = "LYA 2340";
		assertFalse(picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time));
		
		date = "30/01 /2018"; // Tuesday
		time = "16: 31";
		plateNumber = "PPA-2347";
		assertFalse(picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time));
	}
	
	/*
	 * Invalid plates registered
	 */
	@Test
	public void invalidPlate() throws DateTimeException, InvalidPlateNumberException {
		// Test cases
		exception.expect(InvalidPlateNumberException.class);
		plateNumber = "ABC001Y";
		picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time);

		plateNumber = "ABC03";
		picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time);
		
		plateNumber = "ABCD031";
		picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time);
		
		plateNumber = "AB0323";
		picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time);
		
		plateNumber = "ABC03D1";
		picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time);
	}
	
	/*
	 * Invalid date registered
	 */
	@Test
	public void invalidDate() throws DateTimeException, InvalidPlateNumberException {
		// Test cases
		plateNumber = "PPA-2347";
		exception.expect(DateTimeException.class);
		date = "01/31/2018"; // Monday
		time = "18:00";
		picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time);
		
		date = "01/01/2018"; // Monday
		time = "18:00 pm";
		picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time);
	}
}
