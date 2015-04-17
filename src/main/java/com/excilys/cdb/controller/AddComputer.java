package com.excilys.cdb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.cdb.cli.LocalDateTimeUtil;
import com.excilys.cdb.mapper.dtomapper.CompanyDtoMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.dto.ComputerDto;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/addComputer")
public class AddComputer {

	private static Logger logger = LoggerFactory.getLogger(AddComputer.class);
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyDtoMapper dtoMapper;

	@Autowired
	private ComputerService computerService;
		
	@Autowired
	ResourceBundleMessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initDto(Locale locale, ModelMap model) {
		model.addAttribute("companies", dtoMapper.mapList(companyService.getAll()));
		return new ModelAndView("addComputer", "command", new ComputerDto());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doPost(Locale locale, ModelMap model, @ModelAttribute("SpringWeb")ComputerDto computer, RedirectAttributes redirectAttrs) {
		
        // TODO validation		
        String name = computer.getName();
        String introduced = computer.getIntroducedDate();
        String discontinued = computer.getDiscontinuedDate();
        long companyId = computer.getCompanyId();
        logger.debug("name : " + name  + " introduced : " + introduced + " discontinued : " +  discontinued + " companyId : " + companyId);
        if (name != null) {
            name = name.trim();
            if (name.isEmpty()) {
            	model.addAttribute("message", messageSource.getMessage("msg.nameNok", null, locale));
            	return "addComputer";
            }
        } else {
        	model.addAttribute("message", messageSource.getMessage("msg.nameNok", null, locale));
            return "addComputer";
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
        Company company = companyService.getById(companyId);
                
        computerService.create(new Computer(name, introducedTime,
                discontinuedTime, company));
        
        redirectAttrs.addFlashAttribute("message", messageSource.getMessage("msg.addOk", null, locale));
        
        return "redirect:dashboard";
    }

}
