package com.excilys.cdb.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.cli.LocalDateTimeUtil;
import com.excilys.cdb.mapper.dtomapper.CompanyDtoMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Component("addComputer")
@WebServlet(urlPatterns = "/addComputer", name="addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyDtoMapper dtoMapper;

	@Autowired
	private ComputerService computerService;
	
	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void setDtoMapper(CompanyDtoMapper dtoMapper) {
		this.dtoMapper = dtoMapper;
	}
	
	@Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("companies", dtoMapper.mapList(companyService.getAll()));
        getServletContext().getRequestDispatcher(
                "/WEB-INF/views/addComputer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO validation
        String name = req.getParameter("name");
        String introduced = req.getParameter("introduced");
        String discontinued = req.getParameter("discontinued");
        String companyId = req.getParameter("companyId");
        if (name != null) {
            name = name.trim();
            if (name.isEmpty()) {
                req.setAttribute("message", "Name is mandatory");
                getServletContext().getRequestDispatcher(
                        "/WEB-INF/views/addComputer.jsp").forward(req, resp);
                return;
            }
        } else {
            req.setAttribute("message", "Name is mandatory");
            getServletContext().getRequestDispatcher(
                    "/WEB-INF/views/addComputer.jsp").forward(req, resp);
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime introducedTime = null;
        if (introduced != null) {
            introduced = introduced.trim();
            if (!introduced.isEmpty()) {
                introduced = LocalDateTimeUtil.convertToValidLocalDateTime(introduced);
                introducedTime = LocalDateTime.parse(introduced, formatter);
            }
        }
        LocalDateTime discontinuedTime = null;
        if (discontinued != null) {
            discontinued = discontinued.trim();
            if (!discontinued.isEmpty()) {
                discontinued = LocalDateTimeUtil.convertToValidLocalDateTime(discontinued);
                discontinuedTime = LocalDateTime.parse(discontinued, formatter);
            }
        }
        Company company = null;
        if (companyId != null) {
            companyId = companyId.trim();
            if (!companyId.isEmpty()) {
                Long id = Long.valueOf(companyId);
                company = companyService.getById(id);
            }
        }
        computerService.create(new Computer(name, introducedTime,
                discontinuedTime, company));
        resp.sendRedirect("dashboard");
    }

}
