package br.edu.mouralacerda.testebdroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class NovoAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_aluno)

        val botaoSalvar = findViewById<ImageButton>(R.id.btnSalvar)
        val botaoCancelar = findViewById<ImageButton>(R.id.btnCancelar)
        val nomeAluno = findViewById<EditText>(R.id.edtNome)

        botaoSalvar.setOnClickListener {

            if(nomeAluno.text.toString().isEmpty()) {
                Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show()
            } else {
                val a = Aluno(nomeAluno.text.toString(), null)

                Database.getInstance(this)!!.alunoDAO().salvar(a)

                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()

                finish()
            }
        }

        botaoCancelar.setOnClickListener {
            finish()
        }


    }
}