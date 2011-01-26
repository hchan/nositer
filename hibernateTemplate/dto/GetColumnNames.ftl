<#-- // Get All Fields -->

public ArrayList<String> getColumnNames() {
ArrayList<String> retval = new ArrayList<String>();
<#foreach field in pojo.getAllPropertiesIterator()><#t>
retval.add("${field.name}");
</#foreach>
return retval;
}