package program.util;

import program.exception.InvalidPlateNumberException;

/**
 * Utility class for plate number management
 * @author german
 *
 */
public class PlateNumberUtil {
	
	/**
	 * Plate Number parser
	 * @param plateNumber without format
	 * @return plateNumber parsed
	 * @throws InvalidPlateNumberException raised if format is not supported or the plate number is invalid
	 */
	public static String parsePlateNumber(String plateNumber) throws InvalidPlateNumberException {
		String plate = null;
		if (plateNumber == null || plateNumber.length() == 0) {
			throw new InvalidPlateNumberException("The plate number register is empty");
		} else if (plateNumber.length() >= 6) {
			// Remove white spaces
			plateNumber = plateNumber.replaceAll(" +", "");
			// Remove - symbol
			plateNumber = plateNumber.replaceAll("-", "");
			// Regex for plate number validation
			if (plateNumber.matches("^[a-zA-Z]{3}[0-9]{3,4}$")) {
				plate = plateNumber;
			} else {
				throw new InvalidPlateNumberException("The plate number " + plateNumber + " is not valid");
			}
		} else {
			throw new InvalidPlateNumberException("The plate number " + plateNumber + " is incomplete");
		}
		return plate;
	}

}
