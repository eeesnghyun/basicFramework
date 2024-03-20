package com.app.basic.common.utils;

import java.util.HashMap;

import org.springframework.jdbc.support.JdbcUtils;

@SuppressWarnings("serial")
public class LowerHashMap extends HashMap<Object, Object> {

	@Override
	public Object put(Object key, Object value) {
		return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String) key), value);
	}
}
