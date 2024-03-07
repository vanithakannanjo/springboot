package com.vk.bean;

public class UserService {

    private SocialMediaService socialMediaService;
    /*public SocialMediaService getSocialMediaService() {
        return socialMediaService;
    }

    public void setSocialMediaService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }*/


    public UserService(SocialMediaService socialMediaService){
        this.socialMediaService = socialMediaService;
    }


    public void displayFeeds(){
        //SocialMediaApp app = new SocialMediaApp();
        //app.getUserFeeds();

       /*SocialMediaService service = new FacebookService();
        service.getUserFeeds();
        service = new InstagramService();
        service.getUserFeeds();*/

        //approach 3
       /*SocialMediaService instance = SocialMediaServiceFactory.getInstance("facebook");
        instance.getUserFeeds();*/

        //approach 4
        socialMediaService.getUserFeeds();



    }

    public static void main(String[] args) {
        UserService us = new UserService(new FacebookService());
        //us.setSocialMediaService(new InstagramService());
        //us.setSocialMediaService(new FacebookService());
        us.displayFeeds();
    }
}
