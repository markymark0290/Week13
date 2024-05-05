package pet.park.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class Contributor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contributorId; // contributor_id going to convert to snake case
	private String contributorName;
	
	@Column(unique = true)
	private String contributorEmail;
	
	//since PetParks has a contributor and Contributor has PetParks, when Jackson tries to write the 
	//JSON for the code, it will go into an infinite loop.  The exclusions above prevent the recursion.
	
	@EqualsAndHashCode.Exclude //lombok
	@ToString.Exclude
	@OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL) //give it the name of the java variable in the PetPark class
	private Set<PetPark> petParks = new HashSet<>();
}
