package br.edu.mouralacerda.testebdroom

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [Aluno::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun alunoDAO(): AlunoDAO

    companion object {

        private var database: Database? = null
        private val DATABASE = "ALUNOS"

        fun getInstance(context: Context): Database? {
            if(database == null) {
                return criaBanco(context)
            } else {
                return database
            }
        }

        private fun criaBanco(context: Context): Database? {
            return Room.databaseBuilder(
                context, Database::class.java, DATABASE)
                .allowMainThreadQueries().build()
        }
    }
}