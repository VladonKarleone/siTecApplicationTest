package com.example.sitectestapp.di.modules;

import com.example.sitectestapp.MainBaseContract;
import com.example.sitectestapp.MainPresenter;
import com.example.sitectestapp.ui.base.Interactor;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    MainBaseContract.Presenter provideMainPresenter(Interactor.MainInteractor mainInteractor) {
        return new MainPresenter(mainInteractor);
    }
}
