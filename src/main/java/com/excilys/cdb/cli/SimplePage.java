package com.excilys.cdb.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * A simple page without information about entities.
 */
public class SimplePage implements Pageable {
	
	/**
	 * Default property on which apply the sorting
	 */
	final String DEFAULT_PROPERTY = "ID";
	
	private Sort sort;
	private List<String> properties;
	private int page;
	private int size;
	private String textualProperties;
	private boolean previous;

	/**
	 * @param page
	 *            Current page
	 * @param size
	 *            Number of entities
	 * @post getPage() == page getSize() == size getSort().equals(Sort.ASC)
	 *       getProperties().size() == 1
	 *       getProperties().contains(Page.DEFAULT_PROPERTY)
	 */
	public SimplePage(int page, int size) {
		if (page < 1 || size < 0) {
			throw new IllegalArgumentException();
		}
		this.page = page;
		this.size = size;
		this.properties = new ArrayList<>();
		this.properties.add(DEFAULT_PROPERTY);
	}
	
	public SimplePage(int page, int size, boolean previous) {
		this(page, size);
		this.previous = previous;
	}

	/**
	 * @param page
	 *            Current page
	 * @param size
	 *            Number of entities
	 * @param sort
	 *            Current sort
	 * @post getPage() == page getSize() == size getSort().equals(sort)
	 *       getProperties().size() == 1
	 *       getProperties().contains(Page.DEFAULT_PROPERTY)
	 */
	public SimplePage(int page, int size, Sort sort, boolean previous) {
		this(page, size, previous);
		if (sort == null) {
			throw new IllegalArgumentException();
		}
		this.sort = sort;
	}

	/**
	 * @param page
	 *            Current page
	 * @param size
	 *            Number of entities
	 * @param sort
	 *            Current sort
	 * @param properties
	 *            Properties for the sort
	 * @post getPage() == page getSize() == size getSort().equals(sort)
	 *       getProperties().size() == properties.length
	 *       getProperties().containsAll(Arrays.asList(properties))
	 */
	public SimplePage(int page, int size, Sort sort, boolean previous, String... properties) {
		this(page, size, sort, previous);
		if (properties.length > 0) {
			this.properties = Arrays.asList(properties);
		}
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public int getOffset() {
		return (page - 1) * size;
	}

	public Sort getSort() {
		return sort;
	}

	public String getProperties() {
		if (textualProperties == null) {
			final StringBuilder sb = new StringBuilder();
			sb.append(properties.get(0));
			for (int k = 1; k < properties.size(); ++k) {
				sb.append(", " + properties.get(k));
			}
			textualProperties = sb.toString();
		}
		return textualProperties;
	}

	public void setPage(int page) {
		if (page < 1) {
			throw new IllegalArgumentException();
		}
		this.page = page;
	}

	public void setSize(int size) {
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		this.size = size;
	}

	public void setSort(Sort sort) {
		if (sort == null) {
			throw new IllegalArgumentException();
		}
		this.sort = sort;
	}

	public void setProperties(String... properties) {
		if (properties.length > 0) {
			this.properties = Arrays.asList(properties);
		}
	}

	public boolean isPrevious() {
		return previous;
	}

	public void setPrevious(boolean previous) {
		this.previous = previous;
	}

	@Override
	public int getPageNumber() {
		return page;
	}

	@Override
	public int getPageSize() {
		return size;
	}

	@Override
	public Pageable next() {
		return new SimplePage(page+1, size);
	}

	@Override
	public Pageable previousOrFirst() {
		return new SimplePage((page==0)?0:page-1, size);
	}

	@Override
	public Pageable first() {
		return new SimplePage(0, size);
	}

	@Override
	public boolean hasPrevious() {
		return previous;
	}
}
