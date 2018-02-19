package model;

public class WidgetDao {
	public static WidgetDao instance = null;

	public static WidgetDao getInstance() {
		if (instance == null)
			return new WidgetDao();
		else
			return instance;
	}

	private WidgetDao() {}
}
