package br.grupointegrado.jucemard.cadrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class Configuracao extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);}
}


