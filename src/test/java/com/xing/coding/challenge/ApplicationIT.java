package com.xing.coding.challenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.notNullValue;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ApplicationIT {
  private MockMvc mock;
  @Autowired
  private WebApplicationContext webApplicationContext;
  @Before
  public void setup() {
    this.mock = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
  }
	@Test
	public void root() throws Exception {
	  mock.perform(get("/api"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("text/plain;charset=UTF-8"))
      .andExpect(content().string("Welcome to Fructa"));
	}
	@Test
  public void login() throws Exception {
    mock.perform(post("/api/login").content("{\"id\":\"player1\",\"password\":\"password\"}").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().string(""))
      .andExpect(header().string(HttpHeaders.AUTHORIZATION, notNullValue()));
  }
}

