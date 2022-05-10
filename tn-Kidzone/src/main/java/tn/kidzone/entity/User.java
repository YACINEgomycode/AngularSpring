package tn.kidzone.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	
	 String firstName;
	 String lastName;

	@NotBlank
	@Size(max = 50)
	@Email
	 String email;
	 String username;
	@NotBlank
	@Size(max = 120)
	 String password;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	 String image;
	//@Enumerated(EnumType.STRING)
	 //Role role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userApp")
	Set<Appointment> appointments;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userRec")
	Set<Reclamation> reclamations;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userPost")
	@JsonIgnore
	Set<Post> posts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userComment")
	@JsonIgnore
	Set<Comments> comments;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userMessage")
	Set<Message> messages;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userKidzone")
	Set<Kidzone> kidzones;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userNotif")
	Set<Notification> notifs;
	
	@ManyToMany(cascade = CascadeType.ALL)
	Set<Event> events;

	//@ManyToMany(fetch = FetchType.EAGER)
	//Set<Role> roles;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(  name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();



	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}



	//public User(String username, String email, String encode, String firstname, String lastname, String image, Set<String> role) {
	//}

	public User(String firstName, String lastName, String email, String username, String password, String image) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.image = image;
	}
	public User(Long id,String firstName, String lastName, String email, String username, String password, String image) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.image = image;
	}
	public User(Long id) {
		this.id = id;
		
	}
}
