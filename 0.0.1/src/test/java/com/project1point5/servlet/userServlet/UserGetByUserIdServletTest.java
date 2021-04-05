package com.project1point5.servlet.userServlet;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

public class UserGetByUserIdServletTest extends Mockito {

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("role_id")).thenReturn("3");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new UserGetByUserIdServlet().doGet(request,response);

        verify(request, atLeast(1)).getParameter("role_id");
        writer.flush();
        Assert.assertTrue(stringWriter.toString().contains("joe"));
    }
}
