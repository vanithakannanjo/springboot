package com.vk.bean;

public class SocialMediaServiceFactory {
    public static SocialMediaService getInstance(String mediaType){
        if(mediaType.equals("facebook"))
            return new FacebookService();
        if(mediaType.equals("instagram"))
            return new InstagramService();
        return null;

    }
}
