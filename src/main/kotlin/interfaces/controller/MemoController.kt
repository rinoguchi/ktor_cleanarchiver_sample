package interfaces.controller

import domain.Memo
import interfaces.repository.MemoRepository
import io.ktor.features.NotFoundException
import io.ktor.locations.Location
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Location("/memos")
class MemoController : KoinComponent {

    private val memoRepository: MemoRepository by inject()
    private val logger: Logger = LoggerFactory.getLogger("MemoController")
    
    @Location("/{id}")
    data class MemoInput(val id: Int, val body: String? = null)

    @Location("/new/{body}")
    data class MemoPostInput(val body: String)

    data class MemoOutput(val id: Int, val body: String, val keywords: List<String>)
    
    private fun memoToMemoOutput(memo: Memo): MemoOutput {
        return MemoOutput(memo.id, memo.body, memo.keywords!!)
    }
    
    
    fun get(memoInput: MemoInput): MemoOutput {
        logger.info("MemoController.get called.")
        val memo: Memo? = memoRepository.findById(memoInput.id)
        memo ?: throw NotFoundException("memo is not found.")
        return memoToMemoOutput(memo)
    }

    fun post(memoPostInput: MemoPostInput): MemoOutput {
        logger.info("MemoController.post called.")
        val memo: Memo = memoRepository.create(memoPostInput.body)
        return memoToMemoOutput(memo)
    }
    
    fun delete(memoInput: MemoInput) {
        logger.info("MemoController.delete called.")
        memoRepository.delete(memoInput.id)
    }
}

