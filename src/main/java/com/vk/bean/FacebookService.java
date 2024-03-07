package com.vk.bean;

public class FacebookService implements SocialMediaService{

    @Override
    public void getUserFeeds(){
        System.out.println("Loading user feeds from facebook");
    }
}
