package com.ashfaq.dev.snips.Util;

public class Record {
    private int primaryKey;
    private String name;
    private String age;
    private String city;
    private String remarks;
    private String other;


    

    public Record(int primaryKey, String name, String age, String city, String remarks, String other) {
		this.primaryKey = primaryKey;
		this.name = name;
		this.age = age;
		this.city = city;
		this.remarks = remarks;
		this.other = other;
	}

    
    
    
    
    
    
	public String getOther() {
		return other;
	}







	public void setOther(String other) {
		this.other = other;
	}







	public int getPrimaryKey() {
        return primaryKey;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getRemarks() {
        return remarks;
    }







	@Override
	public String toString() {
		return "Record [primaryKey=" + primaryKey + ", name=" + name + ", age=" + age + ", city=" + city + ", remarks="
				+ remarks + ", other=" + other + "]";
	}

    
}

