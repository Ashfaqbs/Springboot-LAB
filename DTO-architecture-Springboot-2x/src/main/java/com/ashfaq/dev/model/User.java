/**
 * Code developed by Ashfaq (© [Year])
 * GitHub: https://github.com/DarkSharkAsh
 */

package com.ashfaq.dev.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//this is setters and getters ,tostring , hashcode etc..
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	private String mail;

	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id")
	private Location location;

}
