package br.grupointegrado.jucemard.cadrealm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private List<Livro> listaLivro;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();
        //utilizacao da base
        //   Realm realm = Realm.getDefaultInstance();
        //  realm.beginTransaction();

        //inserir dados
        // Livro livro = new Livro(3, "joao", "android 1.0", 2015);
        //realm.copyToRealm(livro);

        //  livro  = new Livro(4, "tete", "pyton ", 2014);
        // realm.copyToRealm(livro);

        //alteraccao
        //Livro livro = realm.where(Livro.class).findFirst();
        //livro.setAno(2020);
        //realm.copyToRealm(livro);
        //  List<Livro> livros = realm.where(Livro.class).findAll();

        //  realm.commitTransaction();
        //dados persistidos;
        // realm.close();

        //  Toast.makeText(getBaseContext(),"Registro inseridos",Toast.LENGTH_SHORT).show();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddLivroActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        final ListView lista = (ListView) findViewById(R.id.lvLivro);

        listaLivro = realm.where(Livro.class).findAll();
        LivroAdapter adapter = new LivroAdapter(this, listaLivro);
        lista.setAdapter(adapter);

       lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, EditLivroActivity.class);
                intent.putExtra("ID", listaLivro.get(i).getId());
                startActivity(intent);



    }
          });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
      if (id == R.id.editar)
      {
          Intent intent = new Intent(MainActivity.this, AddLivroActivity.class);
          startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
