package program.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import program.exception.InvalidPlateNumberException;
import program.service.PicoAndPlacaService;

/**
 * Pico and placa servlet using the service
 * 
 * @author german
 *
 */
@WebServlet("picoyplaca")
public class PicoAndPlacaServlet extends HttpServlet {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PicoAndPlacaService picoAndPlacaService;

	/**
	 * Http get render servlet
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		String plateNumber = req.getParameter("plateNumber");
		String date = req.getParameter("date");
		String time = req.getParameter("time");
		
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<body>");
		try {
			String text = "<p>The car with plate number ";
			if (picoAndPlacaService.isCarAtPicoAndPlaca(plateNumber, date, time)) {
				writer.println(text + plateNumber + " cannot be used right now!</p>");
			} else {
				writer.println(text + plateNumber + " can be used normally</p>");
			}
		} catch (DateTimeException | InvalidPlateNumberException e) {

			writer.println("<p>" + e.getMessage() +"</p>");
		}
		writer.print("<a href='/picoyplaca'>Return</a>");
		writer.println("</body>");
		writer.println("</html>");
	}

}
