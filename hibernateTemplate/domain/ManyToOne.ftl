<#if c2h.isManyToOne(property)>
<#foreach column in property.getColumnIterator()>
@Transient
public Integer get${column.name?capitalize}() {
       Integer retval = null;
       try {
              retval = get${property.name?capitalize}().getId();
       } catch (Exception e) {
       }
       return retval;
}
public void set${column.name?capitalize} (Integer ${column.name}) {
       get${property.name?capitalize}().setId(${column.name});
}

</#foreach>
</#if>
