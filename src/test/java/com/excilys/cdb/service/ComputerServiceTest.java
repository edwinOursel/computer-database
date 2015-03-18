package com.excilys.cdb.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Computer;

public class ComputerServiceTest {
	
	@Test
	public void testGetAllThrowsErrorOnNull() {
		//GIVEN
		ComputerService c = ComputerService.INSTANCE;
		
		//WHEN
		try {
			c.getAll(null);
			//THEN
			fail("Should throw an exception");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			fail("Should throw an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetByIdThrowsErrorOnNegative() {
		//GIVEN
		ComputerService c = ComputerService.INSTANCE;
		
		//WHEN
		try {
			c.getById(-1);
			//THEN
			fail("Should throw an exception");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			fail("Should throw an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCreateThrowsErrorOnNull() {
		//GIVEN
		ComputerService c = ComputerService.INSTANCE;
		
		//WHEN
		try {
			c.create(null);
			//THEN
			fail("Should throw an exception");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			fail("Should throw an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testUpdateThrowsErrorOnNull() {
		//GIVEN
		ComputerService c = ComputerService.INSTANCE;
		
		//WHEN
		try {
			c.update(null);
			//THEN
			fail("Should throw an exception");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			fail("Should throw an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testDeleteThrowsErrorOnNegative() {
		//GIVEN
		ComputerService c = ComputerService.INSTANCE;
		
		//WHEN
		try {
			c.delete(-2);
			//THEN
			fail("Should throw an exception");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			fail("Should throw an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	/*
	@Test
	public void testComputerCrud() {
		//GIVEN
		ComputerService c = ComputerService.INSTANCE;
		
		try {
			c.create(new Computer());
		}
	}*/

}
