package org.activiti.demo.listener;

import org.activiti.engine.delegate.CustomPropertiesResolver;
import org.activiti.engine.delegate.DelegateExecution;

import java.util.HashMap;
import java.util.Map;

public class MyCustomPropertiesResolver implements CustomPropertiesResolver {

  @Override
  public Map<String, Object> getCustomPropertiesMap(DelegateExecution execution) {
    Map<String, Object> myMap = new HashMap<String, Object>();
    myMap.put("customProp1", execution.getCurrentActivityId());
    return myMap;
  }

  public Map<String, Object> calledInExpression(String currentActivityId) {
    Map<String, Object> myMap = new HashMap<String, Object>();
    myMap.put("customProp1", currentActivityId);
    return myMap;
  }
}
