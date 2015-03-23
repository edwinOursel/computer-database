package com.excilys.cdb.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class LinkTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private String name = null;
	
	
	public int doStartTag() throws JspException {
		try {
			
			pageContext.getOut().println ("Hello " + this.name + " !");
		} catch (IOException e) {
			throw new JspException ("I/O Error", e);
		}
		return SKIP_BODY;
	}



	public void setName(String name) {
		this.name = name;
	}
	
}
