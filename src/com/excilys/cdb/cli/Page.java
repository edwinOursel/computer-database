package com.excilys.cdb.cli;


/**
 * @inv
 *     getPage() >= 1
 *     1 <= getProperties().size()
 *     getSize() >= 0
 *     getSort() != null
 */
public interface Page {
	enum Sort {
		ASC, DESC;
	}
	/**
	 * Default property on which apply the sorting
	 */
	final String DEFAULT_PROPERTY = "ID";
	/**
	 * @return Current page
	 */
	int getPage();
	/**
	 * @return Number entities by page
	 */
	int getSize();
	/**
	 * @return Offset for this page
	 */
	int getOffset();
	/**
	 * @return Current sort
	 */
	Sort getSort();
	/**
	 * @return Properties on which apply the sort
	 */
	String getProperties();
	/**
	 * @pre page >= 1
	 * @param page Current page
	 */
	void setPage(int page);
	/**
	 * @pre size >= 0
	 * @param size Number entities
	 */
	void setSize(int size);
	/**
	 * @pre sort != null
	 * @param sort Current sort
	 */
	void setSort(Sort sort);
	/**
	 * @pre |properties| > 0
	 * @param properties Properties for the sort
	 */
	void setProperties(String ...properties);
}
