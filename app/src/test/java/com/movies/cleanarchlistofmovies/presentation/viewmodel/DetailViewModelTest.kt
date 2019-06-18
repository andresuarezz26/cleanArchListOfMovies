package com.movies.cleanarchlistofmovies.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.movies.cleanarchlistofmovies.RxImmediateSchedulerRule
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.movies.cleanarchlistofmovies.domain.usecase.GetShowById
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule @JvmField var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getShowUseCase: GetShowById

    @Mock
    private var show: ResultTVMovies? = null

    @InjectMocks
    private lateinit var viewModel: DetailViewModel

    @Test
    fun `When getShow being invoked, notify set value of observer with the right data`() {
        whenever(getShowUseCase(1)).thenReturn(Single.just(show))

        viewModel.getShow(1)

        assertEquals(viewModel.show.value, show)
    }

    @Test
    fun `When view model is disposed, dispose observer`() {
        viewModel.dispose()

        assertTrue(viewModel.compositeDisposable.isDisposed)
    }

}