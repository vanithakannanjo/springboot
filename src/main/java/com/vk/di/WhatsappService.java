package com.vk.di;

import org.springframework.stereotype.Service;

@Service
public class WhatsappService implements  SocialAppService{
    public WhatsappService(){
        System.out.println("whatsapp object created...");
    }

    @Override
    public void getUserFeeds() {
        System.out.println("Getting whatsapp user feeds...");

    }
}
