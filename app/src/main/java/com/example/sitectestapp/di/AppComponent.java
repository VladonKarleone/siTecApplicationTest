package com.example.sitectestapp.di;

import android.app.Application;
import com.example.sitectestapp.SiTecApp;
import com.example.sitectestapp.di.modules.InteractorModule;
import com.example.sitectestapp.di.modules.NetworkModule;
import com.example.sitectestapp.di.modules.ScreenBuilder;
import com.example.sitectestapp.di.modules.UsersModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

@Component(modules = {
        AppModule.class,
        ScreenBuilder.ActivityBuilder.class,
        AndroidInjectionModule.class,
        UsersModule.class,
        NetworkModule.class,
        InteractorModule.class})

@Singleton
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(SiTecApp siTecApp);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder appModule(AppModule appModule);

        @BindsInstance
        Builder networkModule(NetworkModule networkModule);

        @BindsInstance
        Builder usersModule(UsersModule usersModule);

        @BindsInstance
        Builder interactorModule(InteractorModule interactorModule);

        AppComponent build();
    }
}
