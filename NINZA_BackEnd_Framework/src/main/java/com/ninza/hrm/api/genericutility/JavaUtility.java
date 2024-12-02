package com.ninza.hrm.api.genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random random = new Random();
		int ranInt = random.nextInt(1000);
		return ranInt;
	}

	public String date() {
		Date d = new Date();
		String system_date = d.toString().replace(" ", "").replace(":", "_");
		return system_date;

	}

	public String getSystemDateYYYYMMDD() {
		Date d = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String date = sdf.format(d);
		return date;
	}

	public String getRequiredDateYYYYMMDD(int days) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = sdf.format(cal.getTime());
		return reqDate;
	}

}
