package model;

public class RoleDao {
	public static RoleDao instance = null;

	public static RoleDao getInstance() {
		if (instance == null)
			return new RoleDao();
		else
			return instance;
	}

	private RoleDao() {}
}
