package com.movies.cleanarchlistofmovies.data.repositories

import com.movies.cleanarchlistofmovies.RxImmediateSchedulerRule
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
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
class DiscoverMoviesTVRepoTest {
    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var tvRepo: DiscoverTVRepo
    @Mock
    private lateinit var movieRepo: DiscoverMoviesRepo

    @Mock
    private lateinit var resultMovies1: ResultTVMovies
    @Mock
    private lateinit var resultMovies2: ResultTVMovies

    @InjectMocks
    private lateinit var objectUnderTest: DiscoverMoviesTVRepo

    @Test
    fun `When there are data on service, save in the database a combination of the two observables`() {
        val list = listOf(resultMovies1, resultMovies2)
        whenever(tvRepo(MOCK_CATEGORY)).thenReturn(Single.just(list))
        whenever(movieRepo(MOCK_CATEGORY)).thenReturn(Single.just(list))

        objectUnderTest(MOCK_CATEGORY).test().assertOf {
            assertEquals(4, it.values().getOrNull(0)?.size)
        }
    }

    companion object {
        const val MOCK_CATEGORY = "popularity"
    }
}