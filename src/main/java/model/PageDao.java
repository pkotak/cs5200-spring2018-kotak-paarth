package model;

public class PageDao {
	public static PageDao instance = null;

	public static PageDao getInstance() {
		if (instance == null)
			return new PageDao();
		else
			return instance;
	}

	private PageDao() {
	}
}
