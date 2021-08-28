package ru.otus.otuskotlin.marketplace.logics

import kotlinx.coroutines.runBlocking
import marketplace.stubs.Bolt
import ru.otus.otuskotlin.marketplace.backend.common.context.CorStatus
import ru.otus.otuskotlin.marketplace.backend.common.context.MpContext
import ru.otus.otuskotlin.marketplace.backend.common.models.AdIdModel
import ru.otus.otuskotlin.marketplace.backend.common.models.MpStubCase
import ru.otus.otuskotlin.marketplace.backend.common.models.PaginatedModel
import kotlin.test.Test
import kotlin.test.assertEquals

class AdCrudTest {
    @Test
    fun initTest() {
        val crud = AdCrud()
        val context = MpContext(
            stubCase = MpStubCase.INIT_SUCCESS
        )
        runBlocking {
            crud.init(context)
        }
        assertEquals(CorStatus.SUCCESS, context.status)
    }

    @Test
    fun createSuccessTest() {
        val crud = AdCrud()
        val context = MpContext(
            stubCase = MpStubCase.CREATE_SUCCESS,
            requestAd = Bolt.getModel { id = AdIdModel.NONE },
        )
        runBlocking {
            crud.create(context)
        }
        assertEquals(CorStatus.SUCCESS, context.status)
        val expected = Bolt.getModel()
        with(context.responseAd) {
            assertEquals(expected.id, id)
            assertEquals(expected.title, title)
            assertEquals(expected.description, description)
            assertEquals(expected.ownerId, ownerId)
            assertEquals(expected.dealSide, dealSide)
            assertEquals(expected.permissions, permissions)
            assertEquals(expected.visibility, visibility)
        }
    }

    @Test
    fun readSuccessTest() {
        val crud = AdCrud()
        val context = MpContext(
            stubCase = MpStubCase.READ_SUCCESS,
            requestAdId = Bolt.getModel().id,
        )
        runBlocking {
            crud.read(context)
        }
        assertEquals(CorStatus.SUCCESS, context.status)
        val expected = Bolt.getModel()
        with(context.responseAd) {
            assertEquals(expected.id, id)
            assertEquals(expected.title, title)
            assertEquals(expected.description, description)
            assertEquals(expected.ownerId, ownerId)
            assertEquals(expected.dealSide, dealSide)
            assertEquals(expected.permissions, permissions)
            assertEquals(expected.visibility, visibility)
        }
    }

    @Test
    fun updateSuccessTest() {
        val crud = AdCrud()
        val context = MpContext(
            stubCase = MpStubCase.UPDATE_SUCCESS,
            requestAd = Bolt.getModel(),
        )
        runBlocking {
            crud.update(context)
        }
        assertEquals(CorStatus.SUCCESS, context.status)
        val expected = Bolt.getModel()
        with(context.responseAd) {
            assertEquals(expected.id, id)
            assertEquals(expected.title, title)
            assertEquals(expected.description, description)
            assertEquals(expected.ownerId, ownerId)
            assertEquals(expected.dealSide, dealSide)
            assertEquals(expected.permissions, permissions)
            assertEquals(expected.visibility, visibility)
        }
    }

    @Test
    fun deleteSuccessTest() {
        val crud = AdCrud()
        val context = MpContext(
            stubCase = MpStubCase.DELETE_SUCCESS,
            requestAdId = Bolt.getModel().id,
        )
        runBlocking {
            crud.delete(context)
        }
        assertEquals(CorStatus.SUCCESS, context.status)
        val expected = Bolt.getModel()
        with(context.responseAd) {
            assertEquals(expected.id, id)
            assertEquals(expected.title, title)
            assertEquals(expected.description, description)
            assertEquals(expected.ownerId, ownerId)
            assertEquals(expected.dealSide, dealSide)
            assertEquals(expected.permissions, permissions)
            assertEquals(expected.visibility, visibility)
        }
    }

    @Test
    fun searchSuccessTest() {
        val crud = AdCrud()
        val context = MpContext(
            stubCase = MpStubCase.SEARCH_SUCCESS,
            requestPage = PaginatedModel(),
        )
        runBlocking {
            crud.search(context)
        }
        assertEquals(CorStatus.SUCCESS, context.status)
        val expected = Bolt.getModels()
        with(context.responseAds) {
            assertEquals(expected.size, size)
        }
    }

    @Test
    fun offersSuccessTest() {
        val crud = AdCrud()
        val context = MpContext(
            stubCase = MpStubCase.OFFERS_SUCCESS,
            requestPage = PaginatedModel(),
            requestAdId = Bolt.getModel().id
        )
        runBlocking {
            crud.offer(context)
        }
        assertEquals(CorStatus.SUCCESS, context.status)
        val expected = Bolt.getModels()
        with(context.responseAds) {
            assertEquals(expected.size, size)
        }
    }
}
