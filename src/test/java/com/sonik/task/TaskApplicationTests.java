package com.sonik.task;

import com.sonik.task.controller.NumberController;
import com.sonik.task.service.NumberService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskApplicationTests {

	@Autowired
	NumberController numberController;

	@Autowired
	NumberService numberService;

	@Test
	void contextLoads() {
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(numberController).isNotNull();
			softAssertions.assertThat(numberService).isNotNull();
		});
	}

}
