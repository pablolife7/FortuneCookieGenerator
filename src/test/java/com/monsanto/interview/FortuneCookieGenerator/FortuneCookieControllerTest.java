package com.monsanto.interview.FortuneCookieGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(FortuneCookieControllerTest.class);
	
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
    	LOG.info("message_in_return_of_generateFortuneCookie_should_be_different_for_each_client");
    	String uri1 = "/generateFortuneCookie?client=Barney&company=SuperStore";
    	String uri2 = "/generateFortuneCookie?client=Sarah&company=MegaMarket";
    	
    	final FortuneCookie result1 = GSON
    			.fromJson(this.mockMvc
					.perform(get(uri1))
					.andReturn()
					.getResponse()
					.getContentAsString(), FortuneCookie.class);
		
    	final FortuneCookie result2 = GSON
    			.fromJson(this.mockMvc
					.perform(get(uri2))
					.andReturn()
					.getResponse()
					.getContentAsString(), FortuneCookie.class);
		
    	LOG.info("uri = {}", uri1);
    	LOG.info("message = {}", result1.getMessage());
    	LOG.info("uri = {}",uri2);
    	LOG.info("message = {}", result2.getMessage());
		Assert.assertTrue(!Objects.equals(result1.getMessage(), result2.getMessage()));
	}
	 
}
