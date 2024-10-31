package br.edu.mouralacerda.testebdroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listaAlunos = findViewById<ListView>(R.id.lstAlunos)
        val botaoAdd = findViewById<FloatingActionButton>(R.id.btnAdd)

        botaoAdd.setOnClickListener {
            val i = Intent(this, NovoAluno::class.java)
            startActivity(i)
        }

        listaAlunos.setOnItemClickListener { parent, view, position, id ->

            val a = listaAlunos.getItemAtPosition(position) as Aluno

            val intencao = Intent(this, EditarAluno::class.java)

            intencao.putExtra("aluno", a)

            startActivity(intencao)

        }

        listaAlunos.setOnItemLongClickListener { parent, view, position, id ->

            val a = listaAlunos.getItemAtPosition(position) as Aluno

            val builder = AlertDialog.Builder(this)
            builder
                .setTitle("Apagar Aluno")
                .setMessage("Deseja apagar o aluno $a?")
                .setPositiveButton("Sim") { dialog, which ->
                    Database.getInstance(this)!!.alunoDAO().apagar(a)
                    atualizarLista("nome")
                    Toast.makeText(this, "Apagou: $a", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Não") { dialog, which ->
                    Toast.makeText(this, "Não apagou: $a", Toast.LENGTH_LONG).show()
                }
                .show()

            true
        }

    }

    override fun onResume() {
        super.onResume()

        atualizarLista("nome")

    }

    fun atualizarLista(ordenacao: String) {

        var alunos: List<Aluno> = ArrayList<Aluno>()

        if(ordenacao.equals("nome"))
            alunos = Database.getInstance(this)!!.alunoDAO().listarPorNome()
        else if(ordenacao.equals("id"))
            alunos = Database.getInstance(this)!!.alunoDAO().listarPorId()

        val listaAdaptada = ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos)

        val listaAlunos = findViewById<ListView>(R.id.lstAlunos)

        listaAlunos.adapter = listaAdaptada

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // lembrar de colocar o inflater antes do return
        menuInflater.inflate(R.menu.menu_principal, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menuPrincipalNome) {
            atualizarLista("nome")
        } else if(item.itemId == R.id.menuPrincipalId) {
            atualizarLista("id")
        }



        return super.onOptionsItemSelected(item)
    }
}