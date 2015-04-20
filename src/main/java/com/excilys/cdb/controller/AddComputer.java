package com.excilys.cdb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String doPost(Locale locale, ModelMap model, @ModelAttribute("SpringWeb") @Valid ComputerDto computer, 
			BindingResult validationResult, RedirectAttributes redirectAttrs) {
		
        String name = computer.getName();
        String introduced = computer.getIntroduced();
        String discontinued = computer.getDiscontinued();
        long companyId = computer.getCompanyId();
        logger.debug("name : " + name  + " introduced : " + introduced + " discontinued : " +  discontinued + " companyId : " + companyId);
        
        
        if (validationResult.hasErrors()) {
        	for (FieldError e : validationResult.getFieldErrors()) {
        		//validationResult.rejectValue(e.getField(), messageSource.getMessage(e.getField() + "Nok", null, locale));
        		redirectAttrs.addFlashAttribute(e.getField(), messageSource.getMessage(e.getField() + "Nok", null, locale));
            }
        	
        	return "redirect:addComputer";
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(messageSource.getMessage("datePatternShowFull", null, locale));
        LocalDateTime introducedTime = null;
        if (!introduced.isEmpty()) {
	    	if (introduced.length() == 10) {
	    		introduced = introduced.concat(" 00:00:00");
	    	}
	        introducedTime = LocalDateTime.parse(introduced, formatter);
        }
        
        LocalDateTime discontinuedTime = null;
        if (!discontinued.isEmpty()) {
			if (discontinued.length() == 10) {
				discontinued = discontinued.concat(" 00:00:00");
	    	}
			discontinuedTime = LocalDateTime.parse(discontinued, formatter);
        }
        
        Company company = companyService.getById(companyId);
                
        computerService.create(new Computer(name, introducedTime,
                discontinuedTime, company));
        
        redirectAttrs.addFlashAttribute("message", messageSource.getMessage("msg.addOk", null, locale));
        return "redirect:dashboard";
    }

}
