package infrastructure.framework

import org.koin.dsl.module
import infrastructure.repositoryimpl.MemoRepositoryImpl
import interfaces.controller.MemoController
import interfaces.repository.MemoRepository
import usecase.MemoService

val koinModules = module {
    single { MemoController() }
    single { MemoService() }
    factory<MemoRepository> { MemoRepositoryImpl() }
}
