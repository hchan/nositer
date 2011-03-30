
	public ${pojo.getDeclarationName()} clone() {

	       ${pojo.getDeclarationName()} retval = new ${pojo.getDeclarationName()}();
<#foreach property in pojo.getAllPropertiesIterator()>
  <#if property.getValue().getClass().getSimpleName() = "SimpleValue">
     <#if pojo.getJavaTypeName(property, jdk5) = "Date">
     	  retval.set${pojo.getPropertyName(property)}((Date)${property.name}.clone());
     <#else>
     retval.set${pojo.getPropertyName(property)}(new ${pojo.getJavaTypeName(property, jdk5)}(${property.name}));
     </#if>
  </#if>	
</#foreach>

	       return retval;		
	}
	