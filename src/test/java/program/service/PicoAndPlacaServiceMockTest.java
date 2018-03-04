package program.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import program.exception.InvalidPlateNumberException;
import program.util.DateTimeUtil;
import program.util.PlateNumberUtil;

/**
 * Mock testing for utility classes
 * @author german
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PicoAndPlacaService.class, DateTimeUtil.class, PlateNumberUtil.class})
public class PicoAndPlacaServiceMockTest {
	
	private PicoAndPlacaService picoAndPlacaService;
	
	@Before
	public void setUp() {
		picoAndPlacaService = Mockito.spy(new PicoAndPlacaService());
	}
	
	/**
	 * Test with mocks instead of Static methods
	 * @throws InvalidPlateNumberException
	 */
	@Test
	public void shouldPass() throws InvalidPlateNumberException {
		LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 1, 8, 0, 0);
		PowerMockito.mockStatic(DateTimeUtil.class);
		PowerMockito.mockStatic(PlateNumberUtil.class);
		
		// Test Cases
		when(DateTimeUtil.parseDateTime(anyString(), anyString())).thenReturn(localDateTime);		
		when(PlateNumberUtil.parsePlateNumber(anyString())).thenReturn("ABC1231");
		assertTrue(picoAndPlacaService.isCarAtPicoAndPlaca(anyString(), anyString(), anyString()));
		
		// Not in pico and placa
		when(PlateNumberUtil.parsePlateNumber(anyString())).thenReturn("ABC1234");
		assertFalse(picoAndPlacaService.isCarAtPicoAndPlaca(anyString(), anyString(), anyString()));
	}

}
