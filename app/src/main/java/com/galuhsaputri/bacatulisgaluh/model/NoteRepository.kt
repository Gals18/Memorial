package com.galuhsaputri.bacatulisgaluh.model
//nama paket dan letak penyimpan interface untuk desain.

interface NoteRepository {
    fun addNote(note: Note)
    fun getNote(fileName: String): Note
    fun deleteNote(fileName: String): Boolean
}
//kode diatas merupakan aturan interface untuk bagian tambah memo,
//mengambil data dengan tombol baca atau tampilkan
//dan delete untuk tombol hapus.