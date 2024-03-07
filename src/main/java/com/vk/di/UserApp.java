package com.vk.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
public class UserApp {

   // @Autowired
   // @Qualifier("whatsappService")
   // private SocialAppService service;


    private SocialAppService service;

    public UserApp(@Qualifier("whatsappService") SocialAppService service){
        this.service = service;
    }


    public void loadUserFeeds(){
        service.getUserFeeds();
    }

}
