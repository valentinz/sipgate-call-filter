package com.mimacom.io.sipgate.callfilter;

import com.mimacom.io.sipgate.callfilter.domain.Reject;
import com.mimacom.io.sipgate.callfilter.domain.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Valentin Zickner
 */
@RestController
public class InComingCallController {

    private static final String NEW_CALL = "newCall";

    private final PhoneConfiguration phoneConfiguration;

    public InComingCallController(PhoneConfiguration phoneConfiguration) {
        this.phoneConfiguration = phoneConfiguration;
    }

    @RequestMapping(value = "/incoming", produces = MediaType.APPLICATION_XML_VALUE)
    public Response handleIncomingCall(@RequestParam(required = false) String event,
                                       @RequestParam(required = false) String from) {
        Response response = new Response();
        if (Objects.equals(event, NEW_CALL) && isPhoneNumberBlocked(from)) {
            response.Reject.add(new Reject());
        }
        return response;
    }

    private boolean isPhoneNumberBlocked(String from) {
        return from != null && this.phoneConfiguration.getBlockNumberRegex().stream().anyMatch(from::matches);
    }

}
