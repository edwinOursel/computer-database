package com.excilys.cdb.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
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
	
	@Test
	public void testComputerCrud() {
		//GIVEN
		ComputerService c = ComputerService.INSTANCE;
		Computer computer = new Computer();
		Company company = new Company();
		company.setName("Edwin corp");
		long idComputer = 700000000;
		company.setId(idComputer);
		computer.setCompany(company);
		long idCompany = 123456789;
		computer.setId(idCompany);
		LocalDateTime t = LocalDateTime.now();
		computer.setIntroduced(t);
				
		try {
			c.create(computer);
		} catch (ServiceException e) {
			fail("create : should not throw an exception");
		}
		
		try {
			computer = c.getById(idComputer);
			Assert.assertEquals(idComputer, computer.getId());
			Assert.assertEquals(computer.getCompany().getId(), idCompany);
			Assert.assertEquals(computer.getIntroduced(), t);
		} catch (ServiceException e) {
			fail("get by id : should not throw an exception");
		}
		
		try {
			c.delete(idComputer);
		} catch (ServiceException e) {
			fail("delete : should not throw an exception");
		}
		
	}

}
