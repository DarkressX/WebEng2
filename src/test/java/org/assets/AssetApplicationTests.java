package org.assets;

import org.assets.service.BuildingService;
import org.assets.service.StoreyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-integrationtest.properties")
@SpringBootTest
class AssetApplicationTests
{
	@Autowired
	private BuildingService buildingService;

	@Autowired
	private StoreyService storeyService;

	@Autowired
	private MockMvc mvc;

	@Test
	void postBuilding_200() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders
				.post("/api/v2/assets/storeys/buildings")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

}
