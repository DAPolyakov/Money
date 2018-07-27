package yandexschool.dmpolyakov.money.navigation

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.google.gson.Gson
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command


abstract class BaseNavigator(activity: FragmentActivity, val containerId: Int) : SupportAppNavigator(activity, containerId) {

    public override fun applyCommand(command: Command?) {
        Log.v(TAG, "applyCommand " + printCommand(command))
        super.applyCommand(command)
    }

    private fun printCommand(command: Command?): String {
        return Gson().toJson(command)
    }

    protected fun createActivityIntent(screenKey: String, data: Any): Intent? {
        Log.v(TAG, "navigate to activity " + screenKey + " with data = "
                + Gson().toJson(data))
        return null
    }

    override fun createFragment(screenKey: String, data: Any): Fragment? {
        Log.v(TAG, "navigate to fragment " + screenKey + " with data = "
                + Gson().toJson(data))
        return null
    }

    override fun unknownScreen(command: Command?) {
        Log.e(TAG, "unknown screen " + printCommand(command))
    }

    protected abstract fun createOrApply(screenKey: String, transitionData: Array<Any>): Intent

    companion object {
        val TAG: String = BaseNavigator::class.java.simpleName
    }
}
