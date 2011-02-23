<#if clazz.table.catalog?exists>
    <#assign tableNamePrefix>
    	     ${clazz.table.catalog}.<#t>
    </#assign>
<#else>
    <#assign tableNamePrefix>
    </#assign>
</#if>
public static final String TABLENAME = "${tableNamePrefix}${pojo.getDeclarationName()?lower_case}";

public String getTablename() {
       return TABLENAME;
}