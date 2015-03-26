package com.excilys.cdb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.cli.SimplePage;
import com.excilys.cdb.mapper.dtoMapper.ComputerDtoMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.dto.ComputerDto;
import com.excilys.cdb.service.ComputerService;

@WebServlet(urlPatterns = "/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(Dashboard.class);
	private static ComputerService computerService = ComputerService.INSTANCE;
	
	private ComputerDtoMapper dtoMapper = new ComputerDtoMapper();
	
	@Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
		logger.info("Dashboard servlet called");		
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        Page p;
        int currentPage = 1, entitiesByPage = 20, pge = 1;
        if (page != null) {
            page = page.trim();
            if (!page.isEmpty()) {
                currentPage = Integer.valueOf(page);
                pge = currentPage;
            }
        }
        if (size != null) {
            size = size.trim();
            if (!size.isEmpty()) {
                entitiesByPage = Integer.valueOf(size);
            }
        }
        p = new SimplePage(currentPage, entitiesByPage); 
        final int totalEntities = computerService.count();
        int maxPages = (totalEntities / entitiesByPage);
        if (totalEntities % entitiesByPage != 0) {
            ++maxPages;
        }
        request.setAttribute("totalPages", maxPages);
        maxPages = Math.min(maxPages, pge + entitiesByPage - 1);
        request.setAttribute("page", p);
        request.setAttribute("sizePage", entitiesByPage);
        request.setAttribute("maxPages", maxPages);
        request.setAttribute("computers", dtoMapper.mapList(computerService.getAll(p)));        
        request.setAttribute("currentPage", pge);
        request.setAttribute("total", totalEntities);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(
                request, response);
    }

}
