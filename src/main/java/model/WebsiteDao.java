package model;

public class WebsiteDao {
	public static WebsiteDao instance = null;

	public static WebsiteDao getInstance() {
		if (instance == null)
			return new WebsiteDao();
		else
			return instance;
	}

	private WebsiteDao() {}
}
