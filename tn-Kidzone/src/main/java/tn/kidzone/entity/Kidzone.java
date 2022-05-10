package tn.kidzone.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="kidzone")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Kidzone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	 String description;
	 String logo;
	 long nbEmp;
	 Date creationDate;
	 String numTel;
	 long cost;
	 
	 @ManyToOne
	 User userKidzone;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "kidzoneApp")
		Set<Appointment> appointments;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "kidzoneEvent")
		Set<Event> events;
	

}
