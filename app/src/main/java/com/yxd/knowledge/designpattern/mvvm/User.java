package com.yxd.knowledge.designpattern.mvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.yxd.BR;

/**
 * ViewModel
 */
public class User extends BaseObservable {

    private String name;
    private String pwd;
    private boolean isHide;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
        notifyPropertyChanged(BR.hide);
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
    }
}
