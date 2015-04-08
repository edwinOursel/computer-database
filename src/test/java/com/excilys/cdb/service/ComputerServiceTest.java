package com.excilys.cdb.service;

import java.io.FileInputStream;
import java.time.LocalDateTime;

import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerServiceTest extends DBTestCase {
	
	@BeforeClass
	public void createDB() {
		
	}
	
	@Autowired
	private ComputerService computerService;
	
	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}
	
	@Test
	public void testGetAllThrowsErrorOnNull() {
		//GIVEN
		ComputerService c = computerService;
		
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
		ComputerService c = computerService;
		
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
		ComputerService c =  computerService;
		
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
		ComputerService c = computerService;
		
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
		ComputerService c = computerService;
		
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
		ComputerService c = computerService;
		Computer computer = new Computer();
		Company company = new Company();
		company.setName("Edwin corp");
		long idComputer = 700000000;
		company.setId(idComputer);
		computer.setCompany(company);
		long idCompany = 123456789;
		computer.setId(idCompany);
		LocalDateTime t = LocalDateTime.now();
		computer.setIntroducedDate(t);
		LocalDateTime t2 = LocalDateTime.now();
		computer.setDiscontinuedDate(t2);
		computer.setName("Hal9000");
				
		try {
			c.create(computer);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("create : should not throw an exception");
		}
		
		try {
			computer = c.getById(idComputer);
			Assert.assertEquals(idComputer, computer.getId());
			Assert.assertEquals(computer.getCompany().getId(), idCompany);
			Assert.assertEquals(computer.getIntroducedDate(), t);
			Assert.assertEquals(computer.getDiscontinuedDate(), t2);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("get by id : should not throw an exception");
		}
		
		try {
			c.delete(idComputer);
		} catch (ServiceException e) {
			e.printStackTrace();
			fail("delete : should not throw an exception");
		}
		
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/datasets/dbTest.xml"));
	}

}
