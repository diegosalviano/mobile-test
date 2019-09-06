package projetoitau.com.br.projetoitau

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import projetoitau.com.br.projetoitau.view.MainActivity

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    val rule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        onView(withId(R.id.checkBoxCorrente))
            .perform(typeText(""))
    }
}
