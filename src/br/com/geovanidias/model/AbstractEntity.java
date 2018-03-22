package br.com.geovanidias.model;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public AbstractEntity() {

	}

	public AbstractEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractEntity) {
			if (obj.getClass() == this.getClass()) {
				if (getId() != null && ((AbstractEntity) obj).getId() == getId()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " id= " + id;
	}

}
