<#if package_name??>
package ${package_name};
<#assign parent_package_name>
${package_name?substring(0, package_name?last_index_of("."))}
</#assign>
<#assign grandparent_package_name>
${parent_package_name?substring(0, parent_package_name?last_index_of("."))}
</#assign>
import ${grandparent_package_name}.*;
<#else>
${pojo.getPackageDeclaration()}     
</#if>


// Generated ${date} by Hibernate Tools ${version}
// Enhanced by Henry
//import java.util.List;
//import java.util.ArrayList;
<#assign classbody>
@SuppressWarnings("serial")
<#include "PojoTypeDeclaration.ftl"/>, Domain {

<#if !pojo.isInterface()>
<#include "PojoFields.ftl"/>

<#include "PojoConstructors.ftl"/>
   
<#include "PojoPropertyAccessors.ftl"/>

<#include "PojoToString.ftl"/>

<#include "PojoEqualsHashcode.ftl"/>

<#else>
<#include "PojoInterfacePropertyAccessors.ftl"/>

</#if>
<#include "PojoExtraClassCode.ftl"/>

}
</#assign>

${pojo.generateImports()}
${classbody}

