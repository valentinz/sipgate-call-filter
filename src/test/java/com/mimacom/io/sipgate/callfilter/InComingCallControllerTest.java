package com.mimacom.io.sipgate.callfilter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Valentin Zickner
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InComingCallControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void handleIncomingCall_withValidNumber_returnsEmptyResponse() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/incoming")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("event=newCall&from=492111234567&to=4915791234567&direction=in&callId=123456&user[]=Alice&user[]=Bob")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content).isEqualTo("<Response/>");
    }

    @Test
    public void handleIncomingCall_withInvalidNumber_returnsRejectResponse() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                post("/incoming")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("event=newCall&from=4915219449627&to=4915791234567&direction=in&callId=123456&user[]=Alice&user[]=Bob")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML_VALUE))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content).isEqualTo("<Response><Reject/></Response>");
    }

}