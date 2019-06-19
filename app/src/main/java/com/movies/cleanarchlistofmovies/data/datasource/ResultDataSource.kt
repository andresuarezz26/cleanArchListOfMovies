package com.movies.cleanarchlistofmovies.data.datasource

import com.movies.cleanarchlistofmovies.data.entity.RealmResultTVMovies
import com.movies.cleanarchlistofmovies.data.mapper.RealmMapperResultTVMovies
import com.movies.cleanarchlistofmovies.domain.ResultTVMovies
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

interface ResultTVMovieDataSourceFacade {
    fun createOrUpdate(list: List<ResultTVMovies>)
    fun getById(id: Int): ResultTVMovies?
    fun getAll(): List<ResultTVMovies>
}

class RealmResultTVMovieDataSource @Inject constructor(
        private val mapper: RealmMapperResultTVMovies
) : ResultTVMovieDataSourceFacade {

    override fun createOrUpdate(list: List<ResultTVMovies>) {
        Realm.getDefaultInstance().executeTransaction { realm ->
            list.forEach {
                realm.copyToRealmOrUpdate(mapper.transform(it))
            }
        }
    }

    override fun getById(id: Int): ResultTVMovies? =
            Realm.getDefaultInstance().use { realmInstance ->
                val output = realmInstance.where(RealmResultTVMovies::class.java)
                        .equalTo(RealmResultTVMovies.PRIMARY_KEY, id)
                        .findFirst()
                return output?.let { mapper.transform(output) }
            }

    override fun getAll(): List<ResultTVMovies> {
        Realm.getDefaultInstance().use { realmInstance ->
            val output: RealmResults<RealmResultTVMovies> = realmInstance.where(RealmResultTVMovies::class.java)
                    .findAll()
            return mapper.transform(output)
        }
    }
}