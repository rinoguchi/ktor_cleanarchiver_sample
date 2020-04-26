package interfaces.repository

import domain.Memo

interface MemoRepository {
    fun findById(id: Int) : Memo
    fun create(body: String) : Memo
    fun update(id: Int, body: String) : Memo
    fun delete(id: Int)
}