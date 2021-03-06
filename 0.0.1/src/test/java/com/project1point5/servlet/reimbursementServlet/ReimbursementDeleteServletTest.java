package com.project1point5.servlet.reimbursementServlet;

import com.project1point5.service.ReimbursementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReimbursementDeleteServletTest extends Mockito {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    ReimbursementService reimbursementService = mock(ReimbursementService.class);

    @Test
    public void testDoGet() throws Exception{
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReimbursementGetListServlet().doGet(request, response);
        assertFalse(stringWriter.toString().isEmpty());
    }

    @Test
    public void testDoPost() throws Exception{
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReimbursementGetListServlet().doPost(request, response);
        assertFalse(stringWriter.toString().isEmpty());
    }

    @Test
    public void testDoPut() throws Exception{
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReimbursementGetListServlet().doPut(request, response);
        assertFalse(stringWriter.toString().isEmpty());
    }

    @Test
    public void testJsonBuilder() throws Exception{
        StringBuilder jsonBulder = new StringBuilder();
        jsonBulder.append( "{\"reimbursement\": {\"id\": 200}}");
        assertEquals(new ReimbursementDeleteServlet().getReimbursementFromJsonString(jsonBulder), null);
    }
}
