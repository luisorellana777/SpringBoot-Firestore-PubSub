package com.gcp.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.gcp.sender.SenderApplication;

@RestController
public class MessageController {

    @Autowired
    private SenderApplication.PubsubOutboundGateway messagingGateway;

    @PostMapping("/postMessage")
    public RedirectView publishMessage(@RequestParam("message") String message) {
        messagingGateway.sendToPubsub(message);
        return new RedirectView("/");
    }

}