package org.app.scrum.services;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(value = XmlAccessType.NONE)
public class AtomLink implements Serializable {
	private URI href;
	private String rel;
	private String type;
	private String hreflang;
	private String title;
	
    @XmlAttribute(name = "href")
    public URI getHref() {
        return href;
    }
    @XmlAttribute(name = "rel")
    public String getRel() {
        return rel;
    }
    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }
//    @XmlAttribute(name = "hreflang")
//    public String getHreflang() {
//        return hreflang;
//    }
//    @XmlAttribute(name = "title")
//    public String getTitle() {
//        return title;
//    }
	public AtomLink(URI href, String rel, String type, String hreflang,
			String title) {
		super();
		this.href = href;
		this.rel = rel;
		this.type = type;
		this.hreflang = hreflang;
		this.title = title;
	}
	public AtomLink(String href, String rel) throws Exception {
		super();
		this.href = new URI(href);
		this.rel = rel;
		this.type = "text/html";
	}
	public AtomLink() {
		super();
	}	
	
}