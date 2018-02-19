package model;

public class Website {
	private int id;
	private int developerId;
	private String name;
	private String description;
	private String created;
	private String updated;
	private int visits;

	
	public Website() {
		super();
	}

	public Website(int id, int developerId, String name, String description, String created, String updated, int visits) {
		super();
		this.id = id;
		this.developerId = developerId;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	@Override
	public String toString() {
		return "Website [id=" + id + ", developerId=" + developerId + ", name=" + name + ", description=" + description
				+ ", created=" + created + ", updated=" + updated + ", visits=" + visits + "]";
	}

	
}
