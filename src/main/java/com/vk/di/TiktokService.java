package com.vk.di;

import org.springframework.stereotype.Component;

@Component
public class TiktokService implements SocialAppService
{
    @Override
    public void getUserFeeds(){
        System.out.println("feeds loaded from tiktok");
    }
}
