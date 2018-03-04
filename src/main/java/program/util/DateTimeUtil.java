package program.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Utility class for date time management
 * @author german
 *
 */
public class DateTimeUtil {
	
	/**
	 * Date parser from string
	 * @param date without format 
	 * @param time without format
	 * @return LocalDateTime object
	 * @throws DateTimeException raised if the format of the date or time is not supported or if the date or time is invalid
	 */
	public static LocalDateTime parseDateTime(String date, String time) throws DateTimeException {
		LocalDateTime ldt = null;
		// Format support dd/MM/yyyy or dd-MM-yyyy
		if (date == null || time == null || date.length() == 0 || time.length() == 0) {
			throw new DateTimeException("Date or Time values are null");
		} else {
			// Remove white spaces
			date = date.replaceAll(" +", "");
			// Separator for dd MM and YYYY
			String[] dateParam = date.split("[^\\d]");
			if (dateParam.length == 3) {
				LocalDate ld = LocalDate.of(Integer.parseInt(dateParam[2]), Integer.parseInt(dateParam[1]), Integer.parseInt(dateParam[0]));
				// Remove white spaces
				time = time.replaceAll(" +", "");
				// Separator for hour and minute
				String[] timeParam = time.split("[^\\d]");
				if (timeParam.length == 2) {
					LocalTime lt = LocalTime.of(Integer.parseInt(timeParam[0]), Integer.parseInt(timeParam[1]));
					ldt = LocalDateTime.of(ld, lt);	
				} else {
					throw new DateTimeException("Time is malformed");
				}
				
			} else {
				throw new DateTimeException("Date is malformed");
			}
		}
		return ldt;
	}
}
