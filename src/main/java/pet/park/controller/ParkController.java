package pet.park.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.park.controller.model.ContributorData;
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
	
}
