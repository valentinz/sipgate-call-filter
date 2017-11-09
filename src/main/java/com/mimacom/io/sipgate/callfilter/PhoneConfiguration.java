package com.mimacom.io.sipgate.callfilter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valentin Zickner
 */
@Component
@ConfigurationProperties("phone")
public class PhoneConfiguration {

    private List<String> blockNumberRegex = new ArrayList<>();

    public List<String> getBlockNumberRegex() {
        return blockNumberRegex;
    }

    public void setBlockNumberRegex(List<String> blockNumberRegex) {
        this.blockNumberRegex = blockNumberRegex;
    }
}
