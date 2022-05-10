package tn.kidzone.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Comments implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idComment;
	
	String commentContent;
	@Temporal(TemporalType.DATE)
	Date commentDate;
	
	@ManyToOne
	 User userComment;
	
	@ManyToOne
	@JsonBackReference
	Post post;
}
