package com.yuanrong.admin.util;


import com.yuanrong.admin.Enum.EnumBaseStatus;
import com.yuanrong.admin.Enum.IntegerValuedEnum;
import com.yuanrong.common.util.StringUtil;

import java.util.Vector;

public class EnumUtil {
	private static <T extends Enum & IntegerValuedEnum> String getDrop_downByEnum(Class<T> enumClass, IntegerValuedEnum defaultEnumObject) {
		if (enumClass == null) {
			return "";
		}
		StringBuffer drop_downText = new StringBuffer();
		for (IntegerValuedEnum theEnum : enumClass.getEnumConstants()) {
			String selected = "";
			if (defaultEnumObject != null && theEnum.getIndex() == defaultEnumObject.getIndex()) {
				selected = " selected";
			}
			drop_downText.append("<option value=" + theEnum.getIndex() + selected + ">" + theEnum.getName() + "</option>\n");
		}
		return drop_downText.toString();
	}

	public static <T extends Enum & IntegerValuedEnum> String getDrop_down(Class<T> enumClass, String defaultIndex) {
		if (enumClass == null) {
			return "";
		}
		return getDrop_downByEnum(enumClass, valueOf(enumClass, defaultIndex));
	}

	public static <T extends Enum & IntegerValuedEnum> String getDrop_down(Class<T> enumClass, int defaultIndex) {
		if (enumClass == null) {
			return "";
		}
		return getDrop_downByEnum(enumClass, valueOf(enumClass, defaultIndex));
	}

	public static <T extends Enum & IntegerValuedEnum> String getDrop_down(Class<T> enumClass) {
		if (enumClass == null) {
			return "";
		}
		return getDrop_downByEnum(enumClass, null);
	}

	public static void main(String[] args) {
		System.out.println(valueOf(EnumBaseStatus.class, 1));
	}

	public static <T extends Enum & IntegerValuedEnum> IntegerValuedEnum valueOf(Class<T> enumClass, int index) {
		if (enumClass == null) {
			return null;
		}
		for (IntegerValuedEnum theEnum : enumClass.getEnumConstants()) {
			if (theEnum.getIndex() == index) {
				return theEnum;
			}
		}
		return null;
	}

	public static <T extends Enum & IntegerValuedEnum> IntegerValuedEnum valueOf(Class<T> enumClass, String index) {
		if (enumClass == null) {
			return null;
		}
		if (StringUtil.format(index).equals("") || !StringUtil.isInt(index)) {
			return null;
		} else {
			int indexInt = StringUtil.parseInt(index);
			return valueOf(enumClass, indexInt);
		}
	}

	public static <T extends Enum & IntegerValuedEnum> Vector getVector(Class<T> enumClass) {
		if (enumClass == null) {
			return null;
		}
		Vector vector = new Vector();
		for (IntegerValuedEnum theEnum : enumClass.getEnumConstants()) {
			Object[] values = new Object[2];
			values[0] = theEnum.getIndex();
			values[1] = theEnum.getName();
			vector.add(values);
		}
		return vector;
	}
}
