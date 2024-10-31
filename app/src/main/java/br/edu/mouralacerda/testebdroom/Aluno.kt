package br.edu.mouralacerda.testebdroom

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Aluno(
    var nome: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int?
) : Serializable {

    override fun toString(): String {
        return "$id - $nome"
    }
}