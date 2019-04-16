package com.bsoft.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.druid.util.StringUtils;

/**
 * 字符校验.
 * @author Wuyong
 *
 */
public final class FieldValidator {
	private final static String PHONE_PATTERN = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

	private static Matcher match(String regEx, String str) {
		Pattern pattern = Pattern.compile(regEx);
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher;
	}

	/**
	 * 验证手机号.
	 * @param phone
	 * @return boolean
	 */
	public static boolean validatePhone(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return false;
		}

		return match(PHONE_PATTERN, phone).matches();
	}

	/**
	 * 身份证校验.
	 * @param idCard
	 * @return boolean
	 */
	public static boolean validateIdcard(String idCard) {
		if (StringUtils.isEmpty(idCard)) {
			return false;
		}
		return IdcardValidator.isValidatedAllIdcard(idCard);
	}

	/**
	 * 手机号校验.
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(final String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // ��֤�ֻ��
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话校验.
	 * @param str
	 * @return boolean
	 */
	public static boolean isPhone(final String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); 
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); 
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}

	/**
	 * 手机电话校验.
	 * @param str
	 * @return boolean
	 */
	public static boolean validateTel(final String str) {
		if (!isMobile(str)) {
			return isPhone(str);
		}
		return true;
	}
	
	/**
	 * 日期检验.
	 * @param str
	 * @return boolean
	 */
	public static boolean validateDate(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern
				.compile("^((19[\\d]{2})|(20[0,1,2][0-9]))(\\-(([0][1-9])|([1][0-2]))([\\-](([0][1-9])|([1,2][0-9])|([3][0,1])))?)?$"); // ��֤����
		m = p.matcher(str);
		b = m.matches();
		return b;
	}
}
