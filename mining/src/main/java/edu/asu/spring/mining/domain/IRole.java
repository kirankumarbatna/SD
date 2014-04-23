package edu.asu.spring.mining.domain;

public interface IRole {

	/**
	 * Its a getter for Role's ID
	 * @return role id, can be used as  unique identifier for a Role
	 */
	public abstract String getId();

	/**
	 * Its a setter for Role's ID
	 * @param id, can be used as unique identifier for a Role
	 */
	public abstract void setId(String id);

	/**
	 * Its a getter for Role's Name
	 * @return role name, can be used to as name of the Role
	 */
	public abstract String getName();

	/**
	 * Its a setter for Role's Name
	 * @param name, can be used to as name of the Role
	 */
	public abstract void setName(String name);

	/**
	 * Its a getter for Role's description
	 * @return role description, can be used to describe the Role
	 */
	public abstract String getDescription();

	/**
	 * Its a setter for Role's description
	 * @param description, can be used to describe the Role
	 */
	public abstract void setDescription(String description);

	/**
	 * Convert a Role object into string format
	 * prints Role [id="Some_ID + ", name="some_name", description="some_description"]
	 * @return Role details in the form of string
	 */
	public abstract String toString();

}