package org.djr.retrofit2ee;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;

import java.util.TimeZone;

public class ConversionUtils {
	public static DateTime convertUnixToDateTime(Long unixTimestamp, String timezone) {
		return new DateTime(unixTimestamp * 1000, ISOChronology.getInstanceUTC())
				.withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone(timezone)));
	}
}
