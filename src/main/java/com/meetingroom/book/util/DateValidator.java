package com.meetingroom.book.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DateValidator implements ConstraintValidator<DateValidUtil, String> {

	@Override
	public void initialize(DateValidUtil dateValidUtil) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (Optional.ofNullable(value).isPresent()) {
			if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", value)) {
				String[] dateStr = value.split("-");
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, Integer.valueOf(dateStr[0]));
				int month = (Integer.valueOf(dateStr[1]) - 1);
				if (month >= calendar.getActualMinimum(Calendar.MONTH)
						&& month <= calendar.getActualMaximum(Calendar.MONTH)) {
					calendar.set(Calendar.MONTH, month);
					if (Integer.valueOf(dateStr[2]) >= calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
							&& Integer.valueOf(dateStr[2]) <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
						try {
							LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
							return true;
						} catch (DateTimeParseException e) {
							return false;
						}
					}
					return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}

}
