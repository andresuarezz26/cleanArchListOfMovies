package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.RxImmediateSchedulerRule
import com.movies.cleanarchlistofmovies.data.datasource.ResultTVMovieDataSourceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverServiceFacade
import com.movies.cleanarchlistofmovies.data.service.DiscoverServiceFacade.Companion.MOVIE_SHOW
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DiscoverMoviesRepoTest {
    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var discoverService: DiscoverServiceFacade

    @Mock
    private lateinit var dataSource: ResultTVMovieDataSourceFacade

    @Mock
    private lateinit var resultMovies1: ResultTVMovies
    @Mock
    private lateinit var resultMovies2: ResultTVMovies

    @InjectMocks
    private lateinit var repo: DiscoverMoviesRepo

    @Test
    fun `When there are data on service, save in the database a combination of the two observables`() {
        val list = listOf(resultMovies1, resultMovies2)
        whenever(discoverService(MOVIE_SHOW, MOCK_CATEGORY)).thenReturn(Single.just(list))

        repo(MOCK_CATEGORY).test().assertOf {
            assertEquals(2, it.values().getOrNull(0)?.size)
            verify(dataSource).createOrUpdate(any(), any(), any())
        }
    }

    @Test
    fun `When the service fails, get the data from database`() {
        val list = listOf(resultMovies1, resultMovies2)
        whenever(discoverService(MOVIE_SHOW, MOCK_CATEGORY)).thenReturn(Single.error(Throwable()))
        whenever(dataSource.getAllByCategory(MOVIE_SHOW, MOCK_CATEGORY)).thenReturn(list)

        repo(MOCK_CATEGORY).test().assertOf {
            assertEquals(2, it.values().getOrNull(0)?.size)
        }
    }

    companion object {
        const val MOCK_CATEGORY = "popularity"
    }
}