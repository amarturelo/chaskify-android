package com.chaskify.android.model;

/**
 * Created by alberto on 12/12/17.
 */

public class ServerConfigurationListCacheModel {
    private String company_logo;
    private String user_avatar;
    private String user_name;

    public ServerConfigurationListCacheModel() {
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public ServerConfigurationListCacheModel setCompanyLogo(String company_logo) {
        this.company_logo = company_logo;
        return this;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public ServerConfigurationListCacheModel setUserAvatar(String user_avatar) {
        this.user_avatar = user_avatar;
        return this;
    }

    public String getUser_name() {
        return user_name;
    }

    public ServerConfigurationListCacheModel setUserName(String user_name) {
        this.user_name = user_name;
        return this;
    }
}
