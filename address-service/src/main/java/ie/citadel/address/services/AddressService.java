package ie.citadel.address.services;

import ie.citadel.address.model.Address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class AddressService {

	private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
	
	@HystrixCommand(  threadPoolKey="addressThreadPool", fallbackMethod = "buildFallbackAddress", commandProperties= {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",  value="5000")}) 
    public Address getAddressByEircode(String eircode) {
    	
    	logger.info("Getting address for eircode " + eircode + "...");
        
    	//TODO : Real world, use eircode to pull back address from repository.
    	return  new Address().withEircode("D20 AB01").withAddress1("My House").withAddress2("My Street")
    			             .withAddress3("My Town").withAddress4("My locality");
    }
	
    private Address buildFallbackAddress(String eircode) {
    	
    	logger.info("Getting fallback address for eircode " + eircode + "...");
        
    	//TODO : Real world, use eircode to pull back address from repository.
    	return  new Address().withEircode("D20 AB01").withAddress1("Any House").withAddress2("Any Street")
    			             .withAddress3("Any Town").withAddress4("Anywhere");
    }

}
