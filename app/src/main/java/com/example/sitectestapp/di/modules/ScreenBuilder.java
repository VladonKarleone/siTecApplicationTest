package com.example.sitectestapp.di.modules;
;
import com.example.sitectestapp.MainActivity;
import com.example.sitectestapp.ui.users.UsersActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

public abstract class ScreenBuilder {
    @Module
    public abstract static class ActivityBuilder {
        @ContributesAndroidInjector(modules = {UsersModule.class})
        abstract UsersActivity bindUsersActivity();

        @ContributesAndroidInjector(modules = {MainModule.class})
        abstract MainActivity bindMainActivity();
    }
}
