package com.excilys.cdb.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.model.Computer;

public class ComputerRepositoryCustomImpl extends SimpleJpaRepository<Computer, Long>
		implements ComputerRepositoryCustom {
	
	private final EntityManager entityManager;
	private final String COMPUTER_TABLE = "computer";
	private final String COMPANY_TABLE = "company";
	
	public ComputerRepositoryCustomImpl(Class<Computer> domainClass,
			EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}
	
	public List<Computer> findAll(Page page) {		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM ");
		s.append(COMPUTER_TABLE);
		s.append(" compu LEFT OUTER JOIN ");
		s.append(COMPANY_TABLE);
		s.append(" compa ON compu.company_id = compa.id ORDER BY ");
		s.append(page.getProperties());
		s.append(" ");
		s.append(page.getSort().toString());
		s.append(" LIMIT ");
		s.append(page.getSize());
		s.append(" OFFSET ");
		s.append(page.getOffset());
		return entityManager.createQuery(s.toString()).getResultList();
	}
}
