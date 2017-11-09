package com.mimacom.io.sipgate.callfilter.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valentin Zickner
 */
@JacksonXmlRootElement
public class Response {

    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Reject> Reject = new ArrayList<>();
}
