<#if c2h.isManyToOne(property)>
<#foreach column in property.getColumnIterator()>
private Integer ${column.name};

public Integer get${column.name?capitalize}() {
       return this.${column.name};
}
public void set${column.name?capitalize} (Integer ${column.name}) {
       this.${column.name} = ${column.name};
}

</#foreach>
</#if>
