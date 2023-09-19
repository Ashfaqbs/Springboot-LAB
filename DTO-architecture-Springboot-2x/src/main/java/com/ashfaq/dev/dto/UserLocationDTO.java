/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */

package com.ashfaq.dev.dto;

import lombok.Data;

///this DTO contains the user and loaction details server to client 

@Data
public class UserLocationDTO {

	private long userId;
	private String place;
	private double longitude;
	private double latitude;
	private String mail;

}
