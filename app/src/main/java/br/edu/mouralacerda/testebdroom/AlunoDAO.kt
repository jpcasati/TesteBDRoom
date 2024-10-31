package br.edu.mouralacerda.testebdroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface AlunoDAO {

    @Insert
    fun salvar(obj: Aluno)

    @Query("SELECT * FROM Aluno")
    fun listar(): List<Aluno>

    @Query("SELECT * FROM Aluno ORDER BY nome")
    fun listarPorNome(): List<Aluno>

    @Query("SELECT * FROM Aluno ORDER BY id")
    fun listarPorId(): List<Aluno>

    @Delete
    fun apagar(obj: Aluno)

    // @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Update
    fun atualizar(obj: Aluno)
}