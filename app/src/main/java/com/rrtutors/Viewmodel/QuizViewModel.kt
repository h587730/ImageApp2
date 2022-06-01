package com.rrtutors.Viewmodel

import android.app.Application
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.lifecycle.*
import com.rrtutors.Person
import com.rrtutors.Database.RoomSingleton
import com.rrtutors.R
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import java.util.*

private const val TAG1 = "testVM"


class QuizViewModel(application:Application) : ViewModel() {

    private val db: RoomSingleton = RoomSingleton.getInstance(application)

    fun insert(person: Person) {

        doAsync {
            db.personDao().insert(person)
        }
    }

    fun delete(person: Person) {
        doAsync {
            db.personDao().delete(person)
        }
    }

    private var _allPersons: LiveData<List<Person>> = db.personDao().allPersons()
    val allPersons: LiveData<List<Person>>
    get() = _allPersons


    var score = MutableLiveData(0)

    var attempts = MutableLiveData(0)

    var rounds = 0

    val MAX_ROUNDS = 10

    var correctPerson: MutableLiveData<Person?> = MutableLiveData()

    /*val correctPerson: MutableLiveData<Person>
        get() = _correctPerson*/

    var wrongPerson1: MutableLiveData<Person?> = MutableLiveData()

    /*val wrongPerson1: MutableLiveData<Person>
        get() = _wrongPerson1*/

    var wrongPerson2: MutableLiveData<Person?> = MutableLiveData()

    /*val wrongPerson2: MutableLiveData<Person>
        get() = _wrongPerson2*/


    var personList: List<Person> = listOf()


    var randomList: List<MutableLiveData<Person?>> = listOf()



    fun increaseScore() {
        score.value = score.value?.plus(1)
    }

    fun increaseAttempts() {
        attempts.value = attempts.value?.plus(1)
    }


    fun shufflePersonList (): List<Person> {
        return personList.shuffled()
    }

    fun assignQuizValues(randomList: List<Person>) {
        correctPerson.value = randomList.component1()
        wrongPerson1.value = randomList.component2()
        wrongPerson2.value = randomList.component3()

        Log.i(TAG1, "Correctperson: ${correctPerson.value}, wrongPerson1: ${wrongPerson1.value}" +
                ", wrongPerson2: ${wrongPerson2.value}")
    }

    fun randomizeOrder(
        correctPerson: MutableLiveData<Person?>,
        wrongPerson1: MutableLiveData<Person?>,
        wrongPerson2: MutableLiveData<Person?>) : List<MutableLiveData<Person?>> {

        randomList  = listOf(correctPerson, wrongPerson1, wrongPerson2)
        return randomList.shuffled().toList()

    }




}


