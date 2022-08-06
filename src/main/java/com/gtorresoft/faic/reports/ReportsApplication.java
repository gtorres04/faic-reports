package com.gtorresoft.faic.reports;

import com.gtorresoft.google.sheets.application.GoogleSheetsServiceImpl;
import com.gtorresoft.google.sheets.domain.service.GoogleSheetsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsApplication.class, args);
	}

}
