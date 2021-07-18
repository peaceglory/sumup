package com.sumup;

import com.sumup.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(SpringConfig.ARTICLE_PREPROCESSOR)
class PreprocessorApplicationTests {

	@Test
	void contextLoads() {
	}

}
