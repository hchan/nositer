<#if clazz.table.catalog?exists>
    <#assign tableNamePrefix>
    	     ${clazz.table.catalog}.<#t>
    </#assign>
<#else>
    <#assign tableNamePrefix>
    </#assign>
</#if>
public static final String TABLENAME = "${tableNamePrefix}${clazz.table.name?lower_case}";

public String getTablename() {
       return TABLENAME;
}