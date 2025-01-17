package com.doctorspolis;

import com.doctorspolis.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DoctorspolisApiApplicationTests {

	@Autowired
	DoctorRepository doctorRepository;

	@Test
	void contextLoads() {
		doctorRepository.findAll();
	}

}
