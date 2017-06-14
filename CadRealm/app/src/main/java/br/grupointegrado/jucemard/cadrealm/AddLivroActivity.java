package br.grupointegrado.jucemard.cadrealm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.grupointegrado.jucemard.cadrealm.R;
import io.realm.Realm;

public class AddLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_livro);

        final Realm realm = Realm.getDefaultInstance();

        final EditText nome = (EditText) findViewById(R.id.edNome);
        final EditText autor = (EditText) findViewById(R.id.edAutor);
        final EditText ano = (EditText) findViewById(R.id.edAno);
        Button adicionar = (Button) findViewById(R.id.btnAddLivro);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Livro livro = new Livro();
                int proximoId = 1;
                if (realm.where(Livro.class).max("id") != null)
                    proximoId = realm.where(Livro.class).max("id").intValue() + 1;

                livro.setId(proximoId);
                livro.setNome(nome.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setAno(Integer.parseInt(ano.getText().toString()));

                realm.beginTransaction();
                realm.copyToRealm(livro);
                realm.commitTransaction();

                realm.close();

                Toast.makeText(getBaseContext(), "Livro inserido com sucesso", Toast.LENGTH_SHORT).show();

                //voltar pra menu principal

                Intent intent = new Intent(AddLivroActivity.this, MainActivity.class);
                startActivity(intent);

            }


        });

    }
}



