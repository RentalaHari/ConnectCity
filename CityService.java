package com.mc.city.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class CityService {

	final Logger LOGGER = LoggerFactory.getLogger(CityService.class);
	String result = "No";
	
	@Value("${file.name}")
	private String FILE_NAME;

	public String getCityRoute(String cityOrigin, String cityDestination) throws Exception {
		result = "No";
		Resource resource = new ClassPathResource(FILE_NAME);
		InputStream inputStream = resource.getInputStream();

		try {
			byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			String data = new String(bdata, StandardCharsets.UTF_8);

			Pattern pattern = Pattern.compile("\\n");
			Stream<String> cities = pattern.splitAsStream(data);

			cities.forEach(city -> {

				if (cityOrigin.equals(city.split(",")[0].trim()) && cityDestination.equals(city.split(",")[1].trim())) {
					result = "Yes";
					LOGGER.info("City services: "+ city);
				}

			});
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("IOException", e);
		}

		return result;

	}

}
