package com.project1point5.servlet.reimbursementServlet;

import com.project1point5.service.ReimbursementService;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReimbursementGetByUserIdServletTest {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    ReimbursementService reimbursementService = mock(ReimbursementService.class);

    @Test
    void doPost() throws IOException, ServletException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReimbursementGetByIdServlet().doPost(request, response);
        assertFalse(stringWriter.toString().isEmpty());
    }

    @Test
    void doPut() throws IOException, ServletException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReimbursementGetByIdServlet().doPut(request, response);
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

    @Test
    void isInteger(){
        ReimbursementGetByIdServlet reimbursementGetByIdServlet = new ReimbursementGetByIdServlet();
        assertEquals(reimbursementGetByIdServlet.isInteger("2"), true);
        assertFalse(reimbursementGetByIdServlet.isInteger("Test"));
    }
}