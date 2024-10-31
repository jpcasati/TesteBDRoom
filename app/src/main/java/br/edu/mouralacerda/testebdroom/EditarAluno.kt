package br.edu.mouralacerda.testebdroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class EditarAluno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_aluno)

        val a = intent.getSerializableExtra("aluno") as Aluno

        val textoId = findViewById<TextView>(R.id.txtIdAluno)
        val nomeAluno = findViewById<EditText>(R.id.edtEditarNome)

        textoId.text = a.id.toString()
        nomeAluno.setText(a.nome)

        val botaoSalvar = findViewById<ImageButton>(R.id.btnSalvarAlteracao)
        val botaoCancelar = findViewById<ImageButton>(R.id.btnCancelarEdicao)

        botaoSalvar.setOnClickListener {
            a.nome = nomeAluno.text.toString()
            Database.getInstance(this)!!.alunoDAO().atualizar(a)

            finish()
        }

        botaoCancelar.setOnClickListener {
            finish()
        }


    }
}