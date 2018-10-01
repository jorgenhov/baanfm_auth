package com.bfm.baanfm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		BaanfmApplication.class,
		Jsr310JpaConverters.class
})
public class BaanfmApplication {

	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(BaanfmApplication.class, args);
	}
}
