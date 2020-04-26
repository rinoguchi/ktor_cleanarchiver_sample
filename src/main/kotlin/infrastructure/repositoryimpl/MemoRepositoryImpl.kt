package infrastructure.repositoryimpl

import domain.Memo
import infrastructure.dao.MemoEntity
import interfaces.repository.MemoRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import usecase.MemoService

class MemoRepositoryImpl : MemoRepository, KoinComponent {
    private val memoService: MemoService by inject()

    override fun findById(id: Int): Memo? {
        val memoEntity: MemoEntity? = MemoEntity.findById(id)
        memoEntity ?: return null
        return convertToMemo(memoEntity)
    }
    override fun create(body: String): Memo {
        val memoEntity: MemoEntity = MemoEntity.new {
            this.body = body
        }
        return convertToMemo(memoEntity)
    }

    override fun delete(id: Int) {
        MemoEntity.findById(id)?.delete()
    }
    
    private fun convertToMemo(memoEntity: MemoEntity) : Memo {
        val memo = Memo(memoEntity.id.value, memoEntity.body)
        return memoService.attachKeywords(memo)
    }
}