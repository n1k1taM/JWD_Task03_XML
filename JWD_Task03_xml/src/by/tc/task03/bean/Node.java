package by.tc.task03.bean;

import java.io.Serializable;

public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	private NodeType type;
	private String content;

	public Node(NodeType type, String content) {
		super();
		this.type = type;
		this.content = content;
	}

	public Node() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [type=" + type + ", content=" + content + "]";
	}

}
