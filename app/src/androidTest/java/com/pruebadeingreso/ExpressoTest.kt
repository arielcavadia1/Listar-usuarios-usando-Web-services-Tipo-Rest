package com.pruebadeingreso

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import co.com.ceiba.mobile.pruebadeingreso.MainActivity
import co.com.ceiba.mobile.pruebadeingreso.R
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
public class ExpressoTest() {

    @get:Rule
    public var activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )


    /**
     * Verifica que al ingresar string que no se encuentre en la lista de usuarios
     * se muestre la pantalla empty_text
     */

    @Test
    fun emptyList() {
        onView(withId(R.id.editTextSearch)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextSearch)).perform(typeText("test"))
        onView(withId(R.id.empty_text)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    /**
     * Verifica q el usuario exista en la lista de usuarios
     */
    @Test
    fun checkList() {
        onView(withId(R.id.recyclerViewSearchResults))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Moriah.Stanton"))
                )
            )
    }

    /**
     * Verifica que se realice correctamente la busqueda
     */
    @Test
    fun searchTest() {
        onView(withId(R.id.editTextSearch))
            .perform(typeText("Bret"), closeSoftKeyboard())

        onView(withId(R.id.recyclerViewSearchResults))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Bret"))

                )
            )


    }
    /**
     * Verifica que al hacer click en el btn de ver publicaciones
     * se envie y llene correctamente la data del usuario seleccionado
     */
    @Test
    fun sendDataPostActivityTest() {

        onView(withId(R.id.editTextSearch))
            .perform(typeText("Bret"), closeSoftKeyboard())

        onView(withId(R.id.recyclerViewSearchResults))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, MyViewAction.clickChildViewWithId(
                        R.id.btn_view_post
                    )
                )
            );
        onView(withId(R.id.txtName2)).check(matches(withText(containsString("Bret"))));

    }
    /**
     * Verifica el correcto funcionamiento del recyclerview de publicaciones
     */

    @Test
    fun checkPostDataTest() {
        onView(withId(R.id.editTextSearch))
            .perform(typeText("Bret"), closeSoftKeyboard())

        onView(withId(R.id.recyclerViewSearchResults))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, MyViewAction.clickChildViewWithId(
                        R.id.btn_view_post
                    )
                )
            );

        onView(withId(R.id.recyclerViewPostsResults))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("qui est esse"))
                )
            )
    }
}

object MyViewAction {
    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController?, view: View) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
    }
}