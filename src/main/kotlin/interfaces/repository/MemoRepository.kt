package interfaces.repository

import domain.Memo

interface MemoRepository {
    fun findById(id: Int) : Memo?
    fun create(body: String) : Memo
    fun delete(id: Int)
}