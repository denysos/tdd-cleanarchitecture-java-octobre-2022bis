package com.wealhome.e2e;

import com.wealhome.TestContainers;
import com.wealhome.springboot.WealHomeAppLauncher;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("prod")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = WealHomeAppLauncher.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-prod.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class BaseE2E extends TestContainers {

    @Autowired
    protected MockMvc mvc;

}
