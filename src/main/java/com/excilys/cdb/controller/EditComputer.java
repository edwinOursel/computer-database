package com.excilys.cdb.controller;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.cli.LocalDateTimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = "/editComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            id = id.trim();
            if (!id.isEmpty()) {
                Long idComputer = Long.valueOf(id);
                request.setAttribute("computer", ComputerService.INSTANCE.getById(idComputer));
            }
        }
        request.setAttribute("companiesId", CompanyService.INSTANCE.getAllCompaniesId());
        getServletContext().getRequestDispatcher(
                "/WEB-INF/views/editComputer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String introduced = req.getParameter("introduced");
        String discontinued = req.getParameter("discontinued");
        String companyId = req.getParameter("companyId");
        Long computerId;
        if (id != null) {
            id = id.trim();
            if (!id.isEmpty()) {
                computerId = Long.valueOf(id);
            } else {
                return;
                // erreur 500
            }
        } else {
            return;
            // erreur 500
        }
        if (name != null) {
            name = name.trim();
            if (name.isEmpty()) {
                req.setAttribute("message", "Name is mandatory");
                getServletContext().getRequestDispatcher(
                        "/WEB-INF/views/editComputer.jsp").forward(req, resp);
                return;
            }
        } else {
            req.setAttribute("message", "Name is mandatory");
            getServletContext().getRequestDispatcher(
                    "/WEB-INF/views/editComputer.jsp").forward(req, resp);
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
                Long compId = Long.valueOf(companyId);
                company = CompanyService.INSTANCE.getById(compId);
            }
        }
        ComputerService.INSTANCE.update(new Computer(computerId, name, introducedTime,
                discontinuedTime, company));
        resp.sendRedirect("dashboard");
    }

}
