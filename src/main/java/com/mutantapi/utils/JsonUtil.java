package com.mutantapi.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.ResponseTransformer;

public class JsonUtil {

	private JsonUtil() {
	}

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}

	public static String formatDnaString(String body) {
		JsonObject jsonObject = new Gson().fromJson(body, JsonObject.class);
		if (jsonObject == null || jsonObject.toString().equals("")) {
			throw new IllegalArgumentException("Dna is required and can´t be an empty array");
		}
		try {
			String result = jsonObject.get("dna").toString();
			result = result.replace("[", "");
			result = result.replace("]", "");
			result = result.replace('"', '\0');
			return result;
		} catch (Exception e) {
			throw new IllegalArgumentException("Dna is required and can´t be an empty array");
		}
	}
}
