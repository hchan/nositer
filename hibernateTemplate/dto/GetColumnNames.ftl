<#-- // Get All Fields -->

public enum ColumnType {
<#foreach field in pojo.getAllPropertiesIterator()><#t>
<#foreach column in field.getColumnIterator()>
${column.name},
</#foreach>
</#foreach>
}