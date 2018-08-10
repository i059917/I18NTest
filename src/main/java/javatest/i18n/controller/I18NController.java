package javatest.i18n.controller;

import org.springframework.stereotype.Component;

import javatest.i18n.dto.StatusDTO;

@Component
public class I18NController {
	
	public StatusDTO getStatusDTO() {
		
		StatusDTO dto = new StatusDTO();
		System.out.println("Status Key: " + dto.getStatus());
		System.out.println("Status Text: " + dto.getStatusText());
		
		return new StatusDTO();
	}
}
