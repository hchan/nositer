<#-- // Property accessors -->
<#foreach property in pojo.getAllPropertiesIterator()>
<#if pojo.getMetaAttribAsBool(property, "gen-property", true)>
 <#if pojo.hasFieldJavaDoc(property)>    
    /**       
     * ${pojo.getFieldJavaDoc(property, 4)}
     */
</#if>
    <#include "GetPropertyAnnotation.ftl"/>
    ${pojo.getPropertyGetModifiers(property)} ${pojo.getJavaTypeName(property, jdk5)} ${pojo.getGetterSignature(property)}() {
        return this.${property.name};
    }
    
    ${pojo.getPropertySetModifiers(property)} void set${pojo.getPropertyName(property)}(${pojo.getJavaTypeName(property, jdk5)} ${property.name}) {
        this.${property.name} = ${property.name};
    }

<#--
    <#if pojo.getJavaTypeName(property, jdk5)?starts_with("Set<")>
    <#assign javaTypeName>
    ${pojo.getJavaTypeName(property, jdk5)?replace("Set<", "List<")}<#t>
    </#assign>
    public ${javaTypeName} get${pojo.getPropertyName(property)}AsList() {
    	   return new Array${javaTypeName}(get${pojo.getPropertyName(property)}());
    }

    public void set${pojo.getPropertyName(property)}AsList(${javaTypeName} ${property.name}) {
    	   this.${property.name} = new Hash${pojo.getJavaTypeName(property, jdk5)} (${property.name});
    }
    </#if>
-->

<#include "ManyToOne.ftl" />


</#if>
</#foreach>
