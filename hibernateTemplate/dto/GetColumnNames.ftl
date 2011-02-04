<#-- // Get All Fields -->

public enum ColumnType {
<#foreach field in pojo.getAllPropertiesIterator()><#t>
${field.name},
</#foreach>
}