package com.project1point5.servlet.userServlet;

import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

public class UserGetByIdServletTest extends Mockito {

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new UserGetByIdServlet().doGet(request,response);

        verify(request, atLeast(1)).getParameter("id"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        Assert.assertTrue(stringWriter.toString().contains("bob"));
    }

}
