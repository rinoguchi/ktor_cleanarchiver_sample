package interfaces.controller

import domain.Memo
import interfaces.repository.MemoRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MemoController : KoinComponent {

    private val memoRepository: MemoRepository by inject()
    private val logger: Logger = LoggerFactory.getLogger("MemoController")
    
    data class MemoInput(val id: Int, val body: String? = null)

    data class MemoPostInput(val body: String)

    data class MemoOutput(val id: Int, val body: String, val keywords: List<String>)
    
    private fun memoToMemoOutput(memo: Memo): MemoOutput {
        return MemoOutput(memo.id, memo.body, memo.keywords!!)
    }
    
    
    fun get(memoInput: MemoInput): MemoOutput {
        logger.info("MemoController.get called.")
        val memo: Memo = memoRepository.findById(memoInput.id)
        return memoToMemoOutput(memo)
    }

    fun post(memoPostInput: MemoPostInput): MemoOutput {
        logger.info("MemoController.post called.")
        val memo: Memo = memoRepository.create(memoPostInput.body)
        return memoToMemoOutput(memo)
    }
    
    fun put(memoInput: MemoInput): MemoOutput {
        logger.info("MemoController.put called.")
        val memo: Memo = memoRepository.update(memoInput.id, memoInput.body!!)
        return memoToMemoOutput(memo)
    }

    fun delete(memoInput: MemoInput) {
        logger.info("MemoController.delete called.")
        memoRepository.delete(memoInput.id)
    }
}

