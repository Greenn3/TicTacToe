import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable

data class StoreData(val list : List<String>, val next : String)
const val GAMES = "Games"
suspend fun write (id : String, storedData : StoreData){
    Firebase.firestore.collection(GAMES).document(id).set(StoreData.serializer(), storedData)
}

suspend fun read (id: String) : StoreData{
    return  Firebase.firestore.collection(GAMES).document(id).get().data()
}

suspend fun readX (id: String) : Flow <StoreData>{
    return  Firebase.firestore.collection(GAMES).document(id).snapshots.map { it.data() }
}