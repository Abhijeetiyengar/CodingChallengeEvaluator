package com.basic.CodeEvaluator.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getCurrentTimeinString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		String string = dateFormat.format(new Date());
		return string;
	}
	public static String getCurrentTimeinStringddMMyyyyHHmmss() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"ddMMyyyyHHmmss");
		String string = dateFormat.format(new Date());
		return string;
	}

}
