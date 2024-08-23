package com.ashfaq.dev.libs.gjson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyObject {

	@JsonProperty
	String string;

	@JsonProperty
	int i;

	public MyObject(String string, int i) {
		this.string = string;
		this.i = i;
	}

	@Override
	public String toString() {
		return "MyObject [string=" + string + ", i=" + i + "]";
	}

}
