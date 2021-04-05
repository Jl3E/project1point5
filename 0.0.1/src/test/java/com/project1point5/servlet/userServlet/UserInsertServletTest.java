package com.project1point5.servlet.userServlet;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class UserInsertServletTest extends Mockito {

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String json = "{\"user\":{\"id\":10,\"username\":\"josh\",\"password\":\"josh\",\"firstname\":\"josh\",\"lastname\":\"josh\",\"email\":\"josh@josh.com\",\"role_id\":1}}";
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(json)));

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new UserInsertServlet().doPost(request, response);

        verify(request, atLeast(1));
        writer.flush();
        Assert.assertTrue(stringWriter.toString().contains("Success"));
    }
}
