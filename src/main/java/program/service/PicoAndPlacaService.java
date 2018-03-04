package program.service;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.enterprise.context.RequestScoped;

import program.exception.InvalidPlateNumberException;
import program.util.DateTimeUtil;
import program.util.PlateNumberUtil;

/**
  * Pico and Placa main methods
  * @author german
  *
  */
@RequestScoped
public class PicoAndPlacaService {
	
	/**
	 * Main method called from interface
	 * @param plateNumber received from interface
	 * @param date string received from interface
	 * @param time string received from interface
	 * @return Boolean if the inputs are well formed
	 * @throws InvalidPlateNumberException in case the plate number cannot be formated
	 * @throws DateTimeException in case the date or time cannot be formated
	 */
	public Boolean isCarAtPicoAndPlaca(String plateNumber, String date, String time) throws InvalidPlateNumberException, DateTimeException {
		LocalDateTime ldt = DateTimeUtil.parseDateTime(date, time);
		String plate = PlateNumberUtil.parsePlateNumber(plateNumber);
		return isAtPicoAndPlaca(plate, ldt);
	}
	
	
	
	/**
	 * Compare time
	 * @param candidate time
	 * @param begin time
	 * @param end time
	 * @return comparison
	 */
	private static boolean isBetween(LocalTime candidate, LocalTime begin, LocalTime end) {
	    return !candidate.isBefore(begin) && !candidate.isAfter(end);  // Inclusive.
	}
	
	/**
	 * Method that verify if the car is at pico and placa
	 * @param plateNumber
	 * @param currentDateTime
	 * @return validation if the plate can't be in circulation
	 */
	private Boolean isAtPicoAndPlaca(String plateNumber, LocalDateTime currentDateTime) {
		boolean isInPicoAndPlaca = false;
		DayOfWeek currentDay = currentDateTime.toLocalDate().getDayOfWeek();
		// Morning period
		LocalTime beginMorning = LocalTime.of(7, 0);
		LocalTime endMorning = LocalTime.of(9, 30);
		// Afternoon period
		LocalTime beginAfternoon = LocalTime.of(16, 0);
		LocalTime endAfternoon = LocalTime.of(19, 30);
		boolean inHour;
		// Pico and placa horary
		if (isBetween(currentDateTime.toLocalTime(), beginMorning, endMorning) || isBetween(currentDateTime.toLocalTime(), beginAfternoon, endAfternoon)) {
			inHour = true;
		} else { 
			inHour = false;
		}
		// Validate if car is at pico and placa
		switch (Integer.parseInt(plateNumber.substring(plateNumber.length()-1))) {
		case 1:
			if (DayOfWeek.MONDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		case 2:
			if (DayOfWeek.MONDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		case 3:
			if (DayOfWeek.TUESDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		case 4:
			if (DayOfWeek.TUESDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		case 5:
			if (DayOfWeek.WEDNESDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		case 6:
			if (DayOfWeek.WEDNESDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		case 7:
			if (DayOfWeek.THURSDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		case 8:
			if (DayOfWeek.THURSDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		case 9:
			if (DayOfWeek.FRIDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		case 0:
			if (DayOfWeek.FRIDAY.equals(currentDay) && inHour)
				isInPicoAndPlaca = true;
			break;
		default:
			break;
		}
		return isInPicoAndPlaca;
	}

}
