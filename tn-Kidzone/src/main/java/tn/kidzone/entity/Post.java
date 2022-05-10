package tn.kidzone.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable{

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idPost;
	
	String title;
	String description;
	@Temporal(TemporalType.DATE)
	Date postDate;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	String image;
	
	@ManyToOne
	 User userPost;
	 
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="post")
	private Set<Comments> comments;
	
	
		
}
