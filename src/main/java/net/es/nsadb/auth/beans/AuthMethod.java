package net.es.nsadb.auth.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "method")
public enum AuthMethod {
    NONE,
    BASIC,
    OAUTH2
}