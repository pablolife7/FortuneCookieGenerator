package com.monsanto.interview.FortuneCookieGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FortuneCookieGeneratorApplication.class)
public class FortuneCookieControllerTest {
	
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	@Autowired
	private FortuneCookieController fortuneCookieController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(fortuneCookieController)
				.build();
	}

    @Test
	public void message_in_return_of_generateFortuneCookie_should_be_different_for_each_client() throws Exception {
    	final FortuneCookie result1 = GSON
    			.fromJson(this.mockMvc
					.perform(get("/generateFortuneCookie?client=Barney&company=SuperStore"))
					.andReturn()
					.getResponse()
					.getContentAsString(), FortuneCookie.class);
		
    	final FortuneCookie result2 = GSON
    			.fromJson(this.mockMvc
					.perform(get("/generateFortuneCookie?client=Sarah&company=MegaMarket"))
					.andReturn()
					.getResponse()
					.getContentAsString(), FortuneCookie.class);
		
		Assert.assertTrue(!Objects.equals(result1.getMessage(), result2.getMessage()));
	}
	 
}
