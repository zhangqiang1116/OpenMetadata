package org.openmetadata.schema.governance.workflows.elements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Map;
import org.openmetadata.common.utils.CommonUtil;
import org.openmetadata.schema.governance.workflows.elements.nodes.automatedTask.CheckEntityAttributesTaskDefinition;
import org.openmetadata.schema.governance.workflows.elements.nodes.automatedTask.JsonLogicTaskDefinition;
import org.openmetadata.schema.governance.workflows.elements.nodes.automatedTask.PythonWorkflowAutomationTaskDefinition;
import org.openmetadata.schema.governance.workflows.elements.nodes.automatedTask.SetEntityCertificationTaskDefinition;
import org.openmetadata.schema.governance.workflows.elements.nodes.automatedTask.SetGlossaryTermStatusTaskDefinition;
import org.openmetadata.schema.governance.workflows.elements.nodes.endEvent.EndEventDefinition;
import org.openmetadata.schema.governance.workflows.elements.nodes.startEvent.StartEventDefinition;
import org.openmetadata.schema.governance.workflows.elements.nodes.userTask.UserApprovalTaskDefinition;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "subType")
@JsonSubTypes({
  @JsonSubTypes.Type(
      value = CheckEntityAttributesTaskDefinition.class,
      name = "checkEntityAttributesTask"),
  @JsonSubTypes.Type(
      value = SetEntityCertificationTaskDefinition.class,
      name = "setEntityCertificationTask"),
  @JsonSubTypes.Type(value = StartEventDefinition.class, name = "startEvent"),
  @JsonSubTypes.Type(value = EndEventDefinition.class, name = "endEvent"),
  @JsonSubTypes.Type(
      value = SetGlossaryTermStatusTaskDefinition.class,
      name = "setGlossaryTermStatusTask"),
  @JsonSubTypes.Type(value = UserApprovalTaskDefinition.class, name = "userApprovalTask"),
  @JsonSubTypes.Type(
      value = PythonWorkflowAutomationTaskDefinition.class,
      name = "pythonWorkflowAutomationTask"),
  @JsonSubTypes.Type(value = JsonLogicTaskDefinition.class, name = "jsonLogicTask"),
})
public interface WorkflowNodeDefinitionInterface {
  String getType();

  String getSubType();

  String getName();

  String getDisplayName();

  String getDescription();

  default Object getConfig() {
    return null;
  }
  ;

  default List<String> getInputs() {
    return null;
  }
  ;

  default List<String> getOutputs() {
    return null;
  }
  ;

  void setType(String type);

  void setSubType(String subType);

  void setName(String name);

  void setDisplayName(String displayName);

  void setDescription(String description);

  default void setConfig(Map<String, Object> config) {
    /* no-op implementation to be overridden */
  }

  default void setInputs(List<String> inputs) {
    /* no-op implementation to be overridden */
  }

  default void setOutputs(List<String> outputs) {
    /* no-op implementation to be overridden */
  }

  @JsonIgnore
  default String getNodeDisplayName() {
    return CommonUtil.nullOrEmpty(getDisplayName()) ? getName() : getDisplayName();
  }

  @JsonIgnore
  default NodeType getNodeType() {
    return NodeType.fromValue(getType());
  }

  @JsonIgnore
  default NodeSubType getNodeSubType() {
    return NodeSubType.fromValue(getSubType());
  }
}
