# Working with SOAP :/
___

> XML over https.

To make a request using Postman:
1. Select POST as the verb.
2. Under headers use text/xml as content
3. Raw xml as body

JAXB (Java Architecture for XML Binding) is a library that can be 
used to convert a pojo to xml and vice-versa.

>Marshalling = pojo -> xml

>Un-marshalling = xml -> pojo

## To convert xml to pojo (Because that is what I could do without messing up the namespaces)
```
    public static <T> T convertToObject(String xmlInput, Class<T> type){
        log.info("[convertToObject] called with xmlInput {} and class type {}", xmlInput, type.getTypeName());
        try{
            StringReader reader = new StringReader(xmlInput);
            T inputObject = JAXB.unmarshal(reader, type);
            return type.cast(inputObject);
        }catch (Exception e){
            log.info("Got error while un-marshalling the envelop: {}", e.getMessage(), e);
            return null;
        }
    }
```

## Example
Consider the xml(Ignoring namespace):
```
<Root_Element>
    <An_XML_Parent>
        <Child_Element> value </Child_Element>
    </An_XML_Parent>
</Root_Element>
```

Create package.info in the package where your pojos are located:
````
@XmlSchema(
 namespace = "http://some.namespace.root",
 elementFormDefault = XmlNsForm.QUALIFIED,
 xmlns = {
  @XmlNs(prefix="soapenv", namespaceURI="http://some.namespace.root"),
  @XmlNs(prefix="nex", namespaceURI="http://some.namespace.com")
 }
)
package com.shitty.soap.pojos;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
````

The pojos will look like these:
```
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "Root_Element")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootElementPojo {

    @XmlElement(name = "An_XML_Parent")
    private SomeXmlTagPojo someXmlTagPojo;

}
```

```
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SomeXmlTagPojo {
     private String childElement;
 
     @XmlElement(name = "Child_Element")
     public String getChildElement() {
         return childElement;
     }
}
```

//TODO::Will have to rectify repeating namespaces in XML children.