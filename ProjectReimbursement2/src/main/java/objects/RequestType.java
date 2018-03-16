package objects;

import java.io.Serializable;

public class RequestType implements Serializable {

	/* request type object is labeled by an id, and contains a type */

	/**
	 * 
	 */
	private static final long serialVersionUID = -9097438951474604835L;
	public int type_id;
	public String type;

	@Override
	public String toString() {
		return "ReimbType [type_id=" + type_id + ", type=" + type + "]";
	}

	public RequestType(int type_id, String type) {
		super();
		this.type_id = type_id;
		this.type = type;
	}

	public RequestType() {
		super();
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}