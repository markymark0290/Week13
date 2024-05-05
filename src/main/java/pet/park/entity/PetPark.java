package pet.park.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.AnyKeyJavaType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data

public class PetPark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petParkId;
	private String parkName;
	private String directions;
	private String stateOrProvince;
	private String country;
	
	//because we added the @Embeddable annotation to the GeoLocation class, we need to add an @Embedded
	//annotation to the GeoLocation object.
	
	@Embedded
	private GeoLocation geoLocation;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL) //need to also specify the join column.
	@JoinColumn(name = "contributor_id", nullable = false)
	private Contributor contributor;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST) 
	//if we delete a park, we want to delete the join table rows but not the amenity rows which is why
	//we are using persist instead of all
	@JoinTable(name = "pet_park_amenity", 
			joinColumns = @JoinColumn(name = "pet_park_id"),
			inverseJoinColumns = @JoinColumn(name = "amenity_id"))
	private Set<Amenity> amenities = new HashSet<>();

}
