package program.servlet;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.DateTimeException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.TestCase;
import program.exception.InvalidPlateNumberException;
import program.service.PicoAndPlacaService;

/**
 * Testing servlet used for pico and placa
 * @author german
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PicoAndPlacaServletTest extends TestCase {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private PicoAndPlacaService picoAndPlacaService;
	@InjectMocks
	private PicoAndPlacaServlet picoAndPlacaServlet;
	
	@Rule
    public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testServlet() throws IOException, ServletException, DateTimeException, InvalidPlateNumberException {
		when(request.getParameter("plateNumber")).thenReturn("ABC-001");
		when(request.getParameter("date")).thenReturn("01/01/2018");
		when(request.getParameter("time")).thenReturn("8:00");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
		// Test when car is at pico and placa
		when(picoAndPlacaService.isCarAtPicoAndPlaca(anyString(), anyString(), anyString())).thenReturn(true);
        picoAndPlacaServlet.doGet(request, response);
        assertTrue(stringWriter.toString().contains("cannot be used right now"));
        
        // Test when car is not at pico and placa
        when(picoAndPlacaService.isCarAtPicoAndPlaca(anyString(), anyString(), anyString())).thenReturn(false);
        picoAndPlacaServlet.doGet(request, response);
        assertTrue(stringWriter.toString().contains("can be used normally"));
        
        // Test when an exception occur
        when(picoAndPlacaService.isCarAtPicoAndPlaca(anyString(), anyString(), anyString())).thenThrow(new DateTimeException("Error Date Format"));
        //exception.expect(DateTimeException.class);
        picoAndPlacaServlet.doGet(request, response);
        assertTrue(stringWriter.toString().contains("Error Date Format"));
	}
}
