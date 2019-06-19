package com.movies.cleanarchlistofmovies.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.movies.cleanarchlistofmovies.RxImmediateSchedulerRule
import com.movies.cleanarchlistofmovies.domain.usecase.DiscoverTVShowAndMoviesByCategory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule @JvmField var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var discoverTVShowsAndMovies: DiscoverTVShowAndMoviesByCategory

    @InjectMocks
    private lateinit var mainViewModel: MainViewModel

    @Test
    fun `When getTVAndMovies get called and there are tv shows or movies, set value of observer`() {
        whenever(discoverTVShowsAndMovies(DiscoverTVShowAndMoviesByCategory.Param("popularity.asc"))).thenReturn(Single.just(listOf()))

        mainViewModel.getMoviesAndTVShows(MOCK_CATEGORY)

        Assert.assertEquals(mainViewModel.listOfShows.value, "hola")
    }

    companion object {
        const val MOCK_CATEGORY: String = "popular"
    }
}