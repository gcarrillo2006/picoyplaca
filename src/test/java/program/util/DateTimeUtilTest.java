package program.util;

import static org.junit.Assert.assertNotNull;

import java.time.DateTimeException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Testing utility DateTimeUtil
 * @author german
 *
 */
public class DateTimeUtilTest {
	
	private String date;
	
	private String time;
	
	@Rule
    public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void shouldPass() {
		// Test cases
		date = "01-01-2015";
		time = "08:00";
		assertNotNull(DateTimeUtil.parseDateTime(date, time));
		
		date = "01+02-2018"; // Valid because has only one separator
		time = "5?56";
		assertNotNull(DateTimeUtil.parseDateTime(date, time));
	}
	
	@Test
	public void invalidCases() {
		// Test exception cases
		exception.expect(DateTimeException.class);
		date = "31-02-2014"; // Not valid date
		time = "8:00";
		DateTimeUtil.parseDateTime(date, time);
		
		date = "01-31-2015"; // Invalid format
		time = "07:00";
		DateTimeUtil.parseDateTime(date, time);
		
		date = "01++02-2018"; // Date malformed more than one separator
		time = "5?56";
		DateTimeUtil.parseDateTime(date, time);
		
		date = "01+02-2018"; // Time malformed more than one separator
		time = "5-?56";
		DateTimeUtil.parseDateTime(date, time);
	}

}
