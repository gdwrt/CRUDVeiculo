package br.com.geovanidias.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.geovanidias.model.AbstractEntity;

public class EntityConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if(value == null || !value.matches("\\d+")) {
			return null;
		}
		
		return this.getAttributesFrom(uiComponent).get(value);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if(value != null) {
			if(value instanceof AbstractEntity) {
				AbstractEntity entity = (AbstractEntity) value;
				if(entity.getId() != null) {
					addAttributes(uiComponent, entity);
					return entity.getId().toString();
				}
			}
			return value.toString();
		}
		
		
		return "";
	}
	
	public Map<String, Object> getAttributesFrom(UIComponent uiComponent) {
		return uiComponent.getAttributes();
	}
	
	public void addAttributes(UIComponent uiComponent, AbstractEntity entity) {
		this.getAttributesFrom(uiComponent).put(entity.getId().toString(), entity); 
	}
	
	
}
