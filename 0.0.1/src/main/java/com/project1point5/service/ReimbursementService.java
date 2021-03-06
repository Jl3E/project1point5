package com.project1point5.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1point5.dao.ReimbursementDao;
import com.project1point5.model.Reimbursement;

public class ReimbursementService {
	private ReimbursementDao rd;
	private static final Logger LOGGER = Logger.getLogger(ReimbursementService.class);
	
	public ReimbursementService() {
		rd = new ReimbursementDao();
	}
	
	public void createReimbursement(String json) {
		try {
			Reimbursement r = new ObjectMapper().readValue(json, Reimbursement.class);
			LOGGER.debug("JSON from the client was successfully parsed.");
			rd.insert(r);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for a new reimbursement. Is the JSON malformed?");
			e.printStackTrace();
		}
	}

	public void createReimbursement(Reimbursement r){
		rd.insert(r);
	}
	
	public List<Reimbursement> fetchAllReimbursements() {
		return rd.getList();
	}

	public Reimbursement getReimbursementById(int id){
		return rd.getById(id);
	}

	public List<Reimbursement> getReimbursementsByUserID(int id) {
		return rd.getByUserId(id);
	}
	
	public void updateReimbursements(int[][] i, int r) {
		rd.updateList(i, r);
	}
}
