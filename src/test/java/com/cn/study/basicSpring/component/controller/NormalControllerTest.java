package com.cn.study.basicSpring.component.controller;

import com.cn.study.basicSpring.AOP.DemoAnnotationService;
import com.cn.study.basicSpring.AOP.DemoMethodService;
import com.cn.study.basicSpring.component.service.DemoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * NormalController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 28, 2017</pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/config/spring-dataresource.xml")
@WebAppConfiguration("src/main/resources")
public class NormalControllerTest {

       @Resource
     private DemoAnnotationService demoAnnotationService;
        @Resource
     private DemoMethodService demoMethodService;
        @Resource
     private DemoService demoService;

        @Resource
      private WebApplicationContext wac;

//        @Resource
//    private MockHttpServletRequest request;
//
//        @Resource
//   private MockHttpSession session;

      private MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: testpage(Model model)
     */
    @Test
    public void testTestpage() throws Exception {
        mockMvc.perform(get("/normal"))
                .andExpect(status().isOk())
                .andExpect(view().name("/page"))
                .andExpect(forwardedUrl("/views/page.jsp"))
                .andExpect(model().attribute("msg", demoService.saySomeThing()));

    }

    @Test
    public void testRestpage() throws Exception {
        mockMvc.perform(get("/testRest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(demoService.saySomeThing()))
        ;

    }

@Test
    public void testAspectj() throws Exception {
        demoAnnotationService.add();
        demoMethodService.add();
    }




} 
