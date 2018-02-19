package model;

public class PriviledgeDao {
	public static PriviledgeDao instance = null;

	public static PriviledgeDao getInstance() {
		if (instance == null)
			return new PriviledgeDao();
		else
			return instance;
	}

	private PriviledgeDao() {
	}
}
