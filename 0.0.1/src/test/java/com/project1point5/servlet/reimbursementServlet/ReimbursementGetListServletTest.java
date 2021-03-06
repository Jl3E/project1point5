package com.project1point5.servlet.reimbursementServlet;

import com.project1point5.model.Reimbursement;
import com.project1point5.model.User;
import com.project1point5.service.ReimbursementService;
import com.project1point5.util.HideUsernameAndPassword;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementGetListServletTest extends Mockito {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    ReimbursementService reimbursementService = mock(ReimbursementService.class);


    @Test
    public void testDoGet() throws Exception{
        when(reimbursementService.fetchAllReimbursements()).thenReturn(null);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        response.getWriter();

        new ReimbursementGetListServlet().doGet(request,response);
//        verify(reimbursementService, atLeastOnce());
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
    public void testDoDelete() throws Exception{
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        new ReimbursementGetListServlet().doDelete(request, response);
        assertFalse(stringWriter.toString().isEmpty());
    }


}