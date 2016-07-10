package com.theironyard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.controllers.APitunesController;
import com.theironyard.controllers.APitunesRestController;
import com.theironyard.entities.Song;
import com.theironyard.entities.User;
import com.theironyard.services.SongRepository;
import com.theironyard.services.UserRepository;
import org.hibernate.annotations.SourceType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

//	@Test
//	public void btestUpload() throws Exception{
//		MultipartFile audioFile;
//		String artist = "Me";
//		String title = "lauging";
//		String genre = "creepy";
//			File file = new File("laughing.mp3");
//			MockMultipartFile multipartFile = new MockMultipartFile("aMultiPartFile.txt", new FileInputStream(file));
//
//			mockMvc.perform(
//					MockMvcRequestBuilders.post("/upload")
//							.requestAttr("file", multipartFile.getBytes())
//							.contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
//					.andExpect(status().isCreated());
//		}

	@Test
	public void ctestLike() throws Exception {
		Song song = new Song ("me", "this stupid song", "crap", "laugh.mp3", users.findFirstByUsername("alice") );
		songs.save(song);

		User user = users.findFirstByUsername("alice");
		String songname = song.getTitle();

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(song);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/upVote/?1")
						.content(json)
						.contentType("application/json")
		);

		int numLikes = songs.findOne(1).getLikes();

		System.out.println(songs.findOne(1).getTitle());
		System.out.println("number Likes =" + numLikes);
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
