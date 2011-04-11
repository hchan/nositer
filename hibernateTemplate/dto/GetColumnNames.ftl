<#-- // Get All Fields -->

public enum Column {
<#foreach field in pojo.getAllPropertiesIterator()><#t>
<#foreach column in field.getColumnIterator()>
${column.name},
</#foreach>
</#foreach>
}