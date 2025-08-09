package com.dktech.baseandroidviewdktech.ui.camera

import com.dktech.baseandroidviewdktech.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable


@Serializable
data class ConfigSetting(
    val opacity: Int = 50,
    val opacityVisible: Boolean = true,
    val flip: Boolean = false,
    val flash: Boolean = false,
    val lock: Boolean = false,
    val visible: Boolean = false
)


class TraceDrawVM: BaseViewModel() {

    private val _configSetting = MutableStateFlow<ConfigSetting>(ConfigSetting())
    val configSetting = _configSetting.asStateFlow()

    fun switchOpacity(){
        _configSetting.value = _configSetting.value.copy(
            opacityVisible = !_configSetting.value.opacityVisible
        )
    }

    fun adjustOpacity(opacity: Int) {
        _configSetting.value = _configSetting.value.copy(opacity = opacity)
    }

    fun switchFlip(){
        _configSetting.value = _configSetting.value.copy(flip = !_configSetting.value.flip)
    }
    fun switchFlash(){
        _configSetting.value = _configSetting.value.copy(flash = !_configSetting.value.flash)
    }
    fun switchLock(){
        _configSetting.value = _configSetting.value.copy(lock = !_configSetting.value.lock)
    }
    fun switchVisible(){
        _configSetting.value = _configSetting.value.copy(visible = !_configSetting.value.visible)
    }

}