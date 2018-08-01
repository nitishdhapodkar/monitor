package clientcore.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListOutputVo {

	private List<Map<String, String>> attributesAndValues;
	
	public ListOutputVo(List<Map<String, String>> attributesAndValues) {
		this.attributesAndValues = attributesAndValues;
	}
	
	public ListOutputVo() {
		this.attributesAndValues = new ArrayList<>();
	}

	public List<Map<String, String>> getAttributesAndValues() {
		return attributesAndValues;
	}

	public void setAttributesAndValues(List<Map<String, String>> attributesAndValues) {
		this.attributesAndValues = attributesAndValues;
	}

	public void addAttributeMap(Map<String, String> attributeMap) {
		attributesAndValues.add(attributeMap);
	}
	
	public Set<String> getAllDistinctAttributes() {
		
		Set<String> distinctAttributes = new HashSet<>();
		
		for(Map<String, String> attributMap : attributesAndValues) {
			distinctAttributes.addAll(attributMap.keySet());
		}
		
		return distinctAttributes;
	}
}
