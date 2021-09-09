package com.uwaisalqadri.mangaku.data.souce.local

import com.uwaisalqadri.mangaku.data.souce.local.entity.MangaObject
import io.realm.Realm
import io.realm.delete
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class LocalDataSource(
    private val realm: Realm
) {

    fun getAllMangaAsFlowable(): Flow<List<MangaObject>> {
        return realm.objects<MangaObject>().observe()
    }

    fun getAllManga(): List<MangaObject> {
        return realm.objects(MangaObject::class)
    }

    fun addManga(manga: MangaObject) {
        realm.writeBlocking {
            copyToRealm(manga)
        }
    }

    fun deleteManga(mangaId: Int) {
        realm.writeBlocking {
            objects(MangaObject::class).query("mangaId = $0", mangaId).first().delete()
        }
    }

    fun clearAllManga() {
        realm.writeBlocking {
            objects(MangaObject::class).delete()
        }
    }
}