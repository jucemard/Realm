package br.grupointegrado.jucemard.cadrealm;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class EditLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_livro);

        final Realm realm = Realm.getDefaultInstance();

        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID", 0);
        final Livro livro = realm.where(Livro.class).equalTo("id", id).findFirst();

        final EditText nome = (EditText) findViewById(R.id.edtNome);
        final EditText autor = (EditText) findViewById(R.id.edtautor);
        final EditText ano = (EditText) findViewById(R.id.edtano);

        nome.setText(livro.getNome());
        autor.setText(livro.getAutor());
        ano.setText(String.valueOf(livro.getAno()));

        Button alterar = (Button) findViewById(R.id.btneditlivro);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                livro.setNome(nome.getText().toString());
                livro.setAutor(autor.getText().toString());
                livro.setAno(Integer.parseInt(ano.getText().toString()));
                realm.copyToRealm(livro);
                realm.commitTransaction();
                Toast.makeText(getBaseContext(), "Livro atualizado" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditLivroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button deletar = (Button) findViewById(R.id.btndeletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                livro.deleteFromRealm();
                realm.commitTransaction();
                Toast.makeText(getBaseContext(), "Livro deletado" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditLivroActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }
}
