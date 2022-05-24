package com.galuhsaputri.bacatulisgaluh.model
//kode diatas merupakan nama paket.

import android.content.Context
import java.io.File
//Setelah Anda mengimpor kelas dari paket yang berbeda,
// Anda akan dapat merujuk ke kelas itu tanpa menentukan jalur yang sepenuhnya memenuhi syarat untuk kelas tertentu.

class InternalFileRepository(var context: Context) :
    NoteRepository {
//kode tersebut merupakan suatu kelas internalfilerepository.
    private fun noteFile(fileName: String): File = File(noteDirectory(), fileName)
    private fun noteDirectory(): String = context.filesDir.absolutePath

    //merupakan pemberian akses private pada fungsi notefile.

    override fun addNote(note: Note) {
        context.openFileOutput(note.fileName, Context.MODE_PRIVATE).use { output ->
            output.write(note.noteText.toByteArray())
        }
    }
    //fungsi tersebut digunakan untuk menambahkan file  dari aplikasi yang telah disimpan.


    override fun getNote(fileName: String): Note {
        val note = Note(fileName, "")
        context.openFileInput(fileName).use { stream ->
            val text = stream.bufferedReader().use {
                it.readText()
            }
            note.noteText = text
        }
        return note
    }
//kode diatas merupakan kode atau suatu fungsi yang digunakan untuk menampilkan data yang diambil dari kolom inputan

    override fun deleteNote(fileName: String): Boolean {
        return noteFile(fileName).delete()
    }
 //kode diatas merupakan kode yang diambil dari tombol delete kemudian diproses di sini, untuk menghapus data.
}