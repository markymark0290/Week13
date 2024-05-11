package pet.park.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import lombok.extern.slf4j.Slf4j;
import pet.park.controller.model.ContributorData;
import pet.park.controller.model.PetParkData;
import pet.park.service.ParkService;

@RestController //says every method will return a 200/ok status; we expect json to come into the method
//and we will return json from the method as well
@RequestMapping("/pet_park")
@Slf4j //logger; log message at the info level by default
public class ParkController {
	
	@Autowired //makes this spring managed (bean)
	private ParkService parkService;
	
	@PostMapping("/contributor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContributorData insertContributor(@RequestBody ContributorData contributorData) {
		
		
		//tell spring the JSON will be in the request payload/body
		log.info("Creating contributor {}", contributorData);
		return parkService.saveContributor(contributorData);
		
	}
	@PutMapping("/contributor/{contributorId}")
	public ContributorData updateContributor(@PathVariable Long contributorId,
			@RequestBody ContributorData contributorData) {
		contributorData.setContributorId(contributorId);
		log.info("Updating contributor {}", contributorData);
		return parkService.saveContributor(contributorData);
	}
	
	
	@GetMapping("/contributor")
	public List<ContributorData> retrieveAllContributors(){
		log.info("Retrieve all contributors called.");
		return parkService.retrieveAllContributors();
	}
	
	@GetMapping("/contributor/{contributorId}") //need to put variable in curly braces
	public ContributorData retrieveContributorById(@PathVariable Long contributorId) {
		//@PathVariable tells spring that the variable will go into the url.
		log.info("Retrieving contribuotr with ID{}", contributorId);
		return parkService.retrieveContributorsById(contributorId);
		
	}
	
	@DeleteMapping("/contributor")
	public void deleteAllContributors() {
		
		log.info("Attemping to delete all contributors");
		throw new UnsupportedOperationException("Deleting all contributors is not allowed.");
	}
	
	@DeleteMapping("/contributor/{contributorId}")
	public Map<String, String> deleteContributorById (@PathVariable Long contributorId){
		
		log.info("Deleting contributor with ID = {}", contributorId);
		
		parkService.deleteContributorID(contributorId);
		return Map.of("message", "Deletion of contributor with ID =" + 
		contributorId + " was successful.");
	}
	
	@PostMapping("/contributor/{contributorId}/park")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetParkData insertPetPark(@PathVariable Long contributorId, 
			@RequestBody PetParkData petParkData) {
		
		log.info("Creating park {) for contributor with ID ={}", petParkData, contributorId);
		
		return parkService.savePetPark(contributorId, petParkData);
	}
	@PutMapping("/contributor/{contributorId}/park/{parkId}")
	public PetParkData updatePetPark(@PathVariable Long contributorId, 
			@PathVariable Long parkId,
			@RequestBody PetParkData petParkData) {
		
		petParkData.setPetParkId(parkId);
		
		log.info("Creating park {) for contributor with ID ={}", petParkData, contributorId);
		
		return parkService.savePetPark(contributorId, petParkData);
	}
	
	@GetMapping("/contributor/{contributorId}/park/{parkId}")
	public PetParkData retrievePetParkById(@PathVariable Long contributorId,
			@PathVariable Long parkId) {
		
		log.info("Retrieving pet park with ID={} for contributor with Id={}", parkId, contributorId);
		
		return parkService.retrievePetParkById(contributorId, parkId);
		
	}
}
