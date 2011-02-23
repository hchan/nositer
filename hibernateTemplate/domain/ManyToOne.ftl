<#if c2h.isManyToOne(property)>
<#foreach column in property.getColumnIterator()>
public Integer get${column.name?capitalize}() {
       return get${property.name?capitalize}().getId();
}
public void set${column.name?capitalize}(Integer ${column.name}) {
       get${property.name?capitalize}().setId(${column.name});
}

</#foreach>
</#if>
