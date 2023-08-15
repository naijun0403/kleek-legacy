package app.kleek.core

import android.content.ContextWrapper
import app.kleek.reflow.config.VersionConfig

object CoreHelper {

    /**
     * **Warning**: xposed로 후킹된 프로세스에서는 사용할 수 없음.
     *  반드시 사용자가 실행한 앱에서 사용해야 함
     */
    var contextGetter: (() -> ContextWrapper)? = null

    var versionConfigGetter: (() -> VersionConfig)? = null
    var classLoaderGetter: (() -> ClassLoader)? = null

}