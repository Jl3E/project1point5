package com.project1point5.servlet.reimbursementServlet;

import com.project1point5.service.ReimbursementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementUpdateListServletTest extends Mockito {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void doGet() throws IOException, ServletException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReimbursementGetByIdServlet().doGet(request, response);
        assertFalse(stringWriter.toString().isEmpty());
    }

    @Test
    void doPost() throws IOException, ServletException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReimbursementGetByIdServlet().doPost(request, response);
        assertFalse(stringWriter.toString().isEmpty());
    }

    @Test
    void doDelete() throws IOException, ServletException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReimbursementGetByIdServlet().doDelete(request, response);
        assertFalse(stringWriter.toString().isEmpty());
    }
}