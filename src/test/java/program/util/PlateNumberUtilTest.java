package program.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import program.exception.InvalidPlateNumberException;

/**
 * Testing utility PlateNumberUtil
 * @author german
 *
 */
public class PlateNumberUtilTest {
	
	private String plateNumber;
	
	@Rule
    public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void shouldPass() throws InvalidPlateNumberException {
		// Test cases
		plateNumber = "XBA001";
		assertNotNull(PlateNumberUtil.parsePlateNumber(plateNumber));
		
		plateNumber = "XBA-001";
		assertNotNull(PlateNumberUtil.parsePlateNumber(plateNumber));
		
		plateNumber = "XBA - 001";
		assertNotNull(PlateNumberUtil.parsePlateNumber(plateNumber));
		
		plateNumber = "XBA- 0001";
		assertNotNull(PlateNumberUtil.parsePlateNumber(plateNumber));
	}
	
	@Test
	public void invalidCases() throws InvalidPlateNumberException {
		// Test exception cases
		exception.expect(InvalidPlateNumberException.class);
		plateNumber = "ABC";
		PlateNumberUtil.parsePlateNumber(plateNumber);
		
		plateNumber = "000212";
		PlateNumberUtil.parsePlateNumber(plateNumber);
		
		plateNumber = "ABC  212";
		PlateNumberUtil.parsePlateNumber(plateNumber);
		
		plateNumber = "ABC +1212";
		PlateNumberUtil.parsePlateNumber(plateNumber);
		
		plateNumber = "BC0212";
		PlateNumberUtil.parsePlateNumber(plateNumber);
		
	}

}
