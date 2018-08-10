package javatest.i18n.dto;

import javatest.i18n.util.I18NEnabled;
import javatest.i18n.util.Translation;

@I18NEnabled
public class StatusDTO {
	
	private String status = "INACTIVE";
	
	@Translation(textKeyField = "status")
	private String statusText;
	
	public StatusDTO() {
		
	}

	public StatusDTO(String status, String statusText) {
		super();
		this.status = status;
		this.statusText = statusText;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
}
