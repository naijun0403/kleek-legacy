package app.kleek.reflow.compat.oauth

import app.kleek.core.CoreHelper
import app.kleek.reflow.inapp.oauth.NativeOauthHelper
import app.kleek.reflow.logger.Logger

class OauthHelper(
    private val nativeOauthHelper: NativeOauthHelper
) {

    val authorization: String get() {
        val versionConfig = CoreHelper.versionConfigGetter!!.invoke()

        nativeOauthHelper.oauthHelperClass.getDeclaredMethod(versionConfig.oauthHelperGetAuthorization, String::class.java).apply {
            return invoke(
                null,
                nativeOauthHelper.oauthHelperClass.getDeclaredMethod(versionConfig.oauthHelperGetTokenMethod).invoke(
                    nativeOauthHelper.oauthHelper
                ) as String,
            ) as String
        }
    }

    companion object {
        fun create(): OauthHelper {
            val classLoader = CoreHelper.classLoaderGetter!!.invoke()
            val versionConfig = CoreHelper.versionConfigGetter!!.invoke()

            val nativeOauthHelper = NativeOauthHelper(
                oauthHelper = classLoader.loadClass(versionConfig.oauthHelperStaticClass).getDeclaredField(versionConfig.oauthHelperStaticInstanceField).get(null) as Any,
                oauthHelperClass = classLoader.loadClass(versionConfig.oauthHelperClass)
            )

            return OauthHelper(nativeOauthHelper)
        }
    }

}