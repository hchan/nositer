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

/*
@Transient
public void set${column.name?capitalize} (Integer ${column.name}) {
       if (get${property.name?capitalize}() != null) {
              get${property.name?capitalize}().setId(${column.name});
       }
}
*/
</#foreach>
</#if>
