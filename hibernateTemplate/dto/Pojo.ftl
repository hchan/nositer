<#if package_name??>
package ${package_name};
<#assign parent_package_name>
${package_name?substring(0, package_name?last_index_of("."))}
</#assign>
import ${parent_package_name}.*;
<#else>
${pojo.getPackageDeclaration()}     
</#if>

// Generated ${date} by Hibernate Tools ${version}
// Enhanced by Henry

//import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.extjs.gxt.ui.client.data.BeanModelTag;
//import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
<#assign classbody>
@SuppressWarnings("serial")
<#include "PojoTypeDeclaration.ftl"/> , IsSerializable, Cloneable, BeanModelTag, DTO, Comparable
<#if pojo.getDeclarationName()?ends_with("code") && !pojo.getDeclarationName()?contains("Has")>
, Lookupcode
</#if> 
{

<#if !pojo.isInterface()>
<#include "PojoFields.ftl"/>
<#include "StaticFields.ftl"/>
<#include "PojoConstructors.ftl"/>
   
<#include "PojoPropertyAccessors.ftl"/>

<#include "PojoToString.ftl"/>

<#include "PojoEqualsHashcode.ftl"/>

<#else>
<#include "PojoInterfacePropertyAccessors.ftl"/>

</#if>
<#include "PojoExtraClassCode.ftl"/>
<#include "GetCloneableMethod.ftl"/>
<#include "GetColumnNames.ftl"/>
	@Override
	public int compareTo(Object o) {
		DTO otherDTO = (DTO) o;
		return getId().compareTo(otherDTO.getId());
	}
}

</#assign>

${classbody}

