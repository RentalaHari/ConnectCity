package com.mc.city.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.city.service.CityService;

@RestController
public class CityController {
	
	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/connected", method = RequestMethod.GET)
	public String getCityRoute(@RequestParam("origin") String origin, @RequestParam("destination") String destination)
			throws Exception {

		return cityService.getCityRoute(origin, destination);
		
	}

}
