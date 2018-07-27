package yandexschool.dmpolyakov.money.navigation

import android.os.Bundle
import ru.terrakok.cicerone.BaseRouter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace


class MainRouter : BaseRouter() {
    private var navigator: Navigator? = null

    fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    private fun applyCommand(command: Command) {
        navigator?.applyCommands(arrayOf(command))
    }

    fun navigateTo(screenKey: String, data: String) {
        applyCommand(Forward(screenKey, data))
    }

    fun back() {
        applyCommand(Back())
    }

    fun showTrackerScreen() {
        applyCommand(Replace(Screens.TRACKER.name, Bundle()))
    }

    fun showSettingsScreen() {
        applyCommand(Replace(Screens.SETTINGS.name, Bundle()))
    }

    fun showAboutScreen() {
        applyCommand(Replace(Screens.ABOUT.name, Bundle()))
    }
}
