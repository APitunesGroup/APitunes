package com.theironyard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.controllers.APitunesController;
import com.theironyard.controllers.APitunesRestController;
import com.theironyard.entities.User;
import com.theironyard.services.SongRepository;
import com.theironyard.services.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = APitunesApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APitunesApplicationTests {

	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Autowired
	UserRepository users;

	@Autowired
	SongRepository songs;




	@Test
	public void atestAddUser() throws Exception {
		User user = new User ();
		user.setUsername("alice");
		user.setPassword("alkdjfs");

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(user);

		long oldCount = users.count();

		mockMvc.perform(
				MockMvcRequestBuilders.post("/login")
						.content(json)
						.contentType("application/json")
		);

		Assert.assertTrue(users.count() == oldCount + 1);
	}

	@Test
	public void etestLogout() throws Exception {
		ResultActions ra = mockMvc.perform(
				MockMvcRequestBuilders.post("/logout")
		);
		MvcResult result= ra.andReturn();
		MockHttpServletResponse response = result.getResponse();

		int res = response.getStatus();

		Assert.assertTrue(res == 200);
	}
}
