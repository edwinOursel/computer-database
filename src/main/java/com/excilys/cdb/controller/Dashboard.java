package com.excilys.cdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.cli.SimplePage;
import com.excilys.cdb.mapper.dtomapper.ComputerDtoMapper;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {

	private static Logger logger = LoggerFactory.getLogger(Dashboard.class);
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private ComputerDtoMapper dtoMapper;
		
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(ModelMap model, @RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "size", required = false) String size) {
		logger.info("Dashboard servlet called");
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
        model.addAttribute("totalPages", maxPages);
        maxPages = Math.min(maxPages, pge + entitiesByPage - 1);
        model.addAttribute("page", p);
        model.addAttribute("sizePage", entitiesByPage);
        model.addAttribute("maxPages", maxPages);
        model.addAttribute("computers", dtoMapper.mapList(computerService.getAll(p)));        
        model.addAttribute("currentPage", pge);
        model.addAttribute("total", totalEntities);
        return "dashboard";
    }

}
